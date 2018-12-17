package com.xiangbinwang.interceptor;

import com.xiangbinwang.common.ErrorCode;
import com.xiangbinwang.common.Exception.TradeException;
import lombok.Getter;

public class AuthContext {

  private static final ThreadLocal<AuthContext> CONTEXT = new ThreadLocal<>();

  @Getter
  private String brokerId;

  void setBrokerId(String brokerId) {
    this.brokerId = brokerId;
  }

  static void setCurrentContext(AuthContext userContext) {
    CONTEXT.set(userContext);
  }

  static void removeCurrentContext() {
    CONTEXT.remove();
  }

  public static AuthContext get() {
    AuthContext context = CONTEXT.get();
    if (context == null) {
      throw new TradeException(ErrorCode.AUTH_NULL);
    }
    return context;
  }
}
