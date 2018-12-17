package com.xiangbinwang.common.Exception;

import com.xiangbinwang.common.ErrorCode;

public class TradeException extends RuntimeException{
  private ErrorCode errorCode;
  public TradeException(ErrorCode errorCode) {
    super(errorCode.getErrorMsg());
    this.errorCode = errorCode;
  }

  public TradeException(ErrorCode errorCode, Throwable cause) {
    super(errorCode.getErrorMsg(), cause);
    this.errorCode = errorCode;
  }

  public TradeException(ErrorCode errorCode, String errorMessage) {
    super(errorCode.getErrorMsg() + errorMessage);
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

}
