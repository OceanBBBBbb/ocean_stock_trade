package com.xiangbinwang.filter;


import com.xiangbinwang.interceptor.AuthServletRequestWrapper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Lxa on 2018/6/27.
 *update by Bhy on 2018/12/15
 */
@Slf4j
@WebFilter(filterName="authServletRequestFilter",urlPatterns="/*")
public class AuthServletRequestFilter implements Filter {

  @Override
  public void destroy() {
    log.info("AuthServletRequestFilter destroy");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    chain.doFilter(new AuthServletRequestWrapper((HttpServletRequest) request), response);
  }

  @Override
  public void init(FilterConfig config) throws ServletException {
    log.info("AuthServletRequestFilter init");
  }

}
