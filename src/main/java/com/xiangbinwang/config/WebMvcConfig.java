package com.xiangbinwang.config;

import com.xiangbinwang.dao.mappers.UserKeyMapper;
import com.xiangbinwang.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Lxa on 2018/6/12.
 *
 * @author lixianan
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private UserKeyMapper userKeyMapper;
  //增加拦截器
  public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(new AuthInterceptor(userKeyMapper)).excludePathPatterns(
        "/swagger-ui.html",
        "/swagger-resources/**"
    );
  }

}
