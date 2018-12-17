package com.xiangbinwang.interceptor;

import com.xiangbinwang.common.AuthCommon.Auth;
import com.xiangbinwang.common.ErrorCode;
import com.xiangbinwang.common.Exception.TradeException;
import com.xiangbinwang.dao.mappers.UserKeyMapper;
import com.xiangbinwang.interceptor.BrokerKey.Method;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {


  private static final Comparator<Entry<String, Object>> keyComparator = (o1, o2) -> o1.getKey().compareTo(o2.getKey());

  private final UserKeyMapper userKeyMapper;
  public AuthInterceptor(UserKeyMapper userKeyMapper) {
    this.userKeyMapper = userKeyMapper;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    Auth auth = handler instanceof ResourceHttpRequestHandler ? Auth.Ignore : handler instanceof HandlerMethod ? getAuth((HandlerMethod) handler) : Auth.Required;
    if (auth == Auth.Ignore) {
      return true;
    }
    if (!(request instanceof AuthServletRequestWrapper)) {
      throw new RuntimeException("request类型错误,AuthServletRequestFilter拦截器未生效");
    }
    log.debug("认证拦截:accessKey=[{}]", request.getHeader("accessKey"));
    BrokerKey broker = userKeyMapper.getUser(request.getHeader("accessKey"));
    if (broker == null || StringUtils.isEmpty(broker.getBrokerId())) {
      log.error("认证拦截:accessKey=[{}],broker=[{}]", request.getHeader("accessKey"), broker);
      throw new TradeException(ErrorCode.PARAM_ERROR,"用户不存在");
    }
    String sign = Optional.ofNullable(request.getHeader("sign")).orElse("").toUpperCase(); // 获取sign
    String generateSign = generateSign(((AuthServletRequestWrapper) request).getBody(), Method.get(broker.getMethod()), broker.getSecretKey()).toUpperCase();
    log.debug("认证拦截, sign=[{}], genSign=[{}]", sign, generateSign);
    if (!sign.equals(generateSign)) {
      log.info("参数错误：" + generateSign);
      throw new TradeException(ErrorCode.AUTH_INFO_ERROR);
    }

    // 2018/7/2 校验绑定IP
//    String clientIp = getClientIP(request);
//    List<String> brokerIps = brokerService.getBrokerBindIp(broker.getBrokerId()).stream().map(BrokerIps::getIp).collect(Collectors.toList());
//    if (!brokerIps.contains(clientIp)) {
//      throw new TradeException(ErrorCode.IP_ERROR,clientIp);
//    }

    AuthContext authContext = new AuthContext();
    authContext.setBrokerId(broker.getBrokerId());
    AuthContext.setCurrentContext(authContext);
    log.debug("认证拦截通过：brokerId=[{}]", broker.getBrokerId());
    return true;

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    AuthContext.removeCurrentContext();
    log.info("认证拦截完成");
  }

  private Auth getAuth(HandlerMethod handler) {
    AuthRequired methodAuth = AnnotationUtils.findAnnotation(handler.getMethod(), AuthRequired.class);
    if (methodAuth == null) {
      AuthRequired classAuth = AnnotationUtils.findAnnotation(handler.getBeanType(), AuthRequired.class);
      return classAuth == null ? Auth.Ignore : classAuth.value();
    }
    return methodAuth.value();
  }

  private String generateSign(String data, Method method, String secretKey) {
    String result = data + secretKey;
    log.info("认证拦截：加密明文：" + result);
    if (Method.SHA256.equals(method)) {
      log.debug("sha256加密");
      return DigestUtils.sha256Hex(result).toUpperCase();
    } else {
      throw new TradeException(ErrorCode.AUTH_UNSUPT);
    }
  }

  private String getClientIP(HttpServletRequest request) {
    String source = "X-Real-IP";
    String ip = request.getHeader("X-Real-IP");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      source = "X-Forwarded-For";
      ip = request.getHeader("X-Forwarded-For");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      source = "request.getRemoteAddr()";
      ip = request.getRemoteAddr();
    }
    log.info("Client IP: " + ip + ", fromSource: " + source);
    return ip;
  }

}
