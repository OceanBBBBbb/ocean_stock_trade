package com.xiangbinwang.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ApiResponse<T> {

  @ApiModelProperty(value = "响应成功", required = true)
  private boolean ret;
  @ApiModelProperty(value = "响应码", required = true)
  private String code;
  private String msg;
  private T data;

  private ApiResponse(boolean ret, String code, String msg, T data) {
    this.ret = ret;
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  // 成功时
  public ApiResponse(T data) {
    this(true, ErrorCode.SUCCESS.getErrorCode(), "", data);
  }

  // 失败时
  public ApiResponse(String errorCode, String errorMsg) {
    this(false, errorCode, errorMsg, null);
  }


}
