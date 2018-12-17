package com.xiangbinwang.common;

/**
 * kyc的错误编码
 * Created by binhy on 2018/12/7
 */
public class ErrorCode {

   // * 成功
  public static final ErrorCode SUCCESS = new ErrorCode("SUCCESS", "成功");

   // * 失败
  public static final ErrorCode FAIL = new ErrorCode("FAIL", "系统异常");

   // * 参数错误
  public static final ErrorCode PARAM_ERROR = new ErrorCode("PARAM_ERROR", "参数错误");
  public static final ErrorCode AUTH_INFO_ERROR = new ErrorCode("AUTH_INFO_ERROR", "认证信息错误");
  public static final ErrorCode IP_ERROR = new ErrorCode("IP_ERROR", "IP未绑定");
  public static final ErrorCode AUTH_NULL = new ErrorCode("AUTH_NULL", "认证信息为空");
  public static final ErrorCode AUTH_UNSUPT = new ErrorCode("AUTH_UNSUPT", "不支持的签名类型");
  public static final ErrorCode FILE_NUM_ERROR = new ErrorCode("FILE_NUM_ERROR", "附件数量未达指定要求,");
  public static final ErrorCode AUDIT_STATUS_ERROR = new ErrorCode("AUDIT_STATUS_ERROR", "审核状态编码错误");

   // * 存储错误
  public static final ErrorCode DATA_DEAL_ERROR = new ErrorCode("DATA_DEAL_ERROR", "提交资料处理异常");
  public static final ErrorCode FILE_SAVE_ERROR = new ErrorCode("FILE_SAVE_ERROR", "文件处理异常");
  public static final ErrorCode FILE_TYPE_ERROR = new ErrorCode("FILE_TYPE_ERROR", "文件类型异常");
  public static final ErrorCode FILE_TYPE_UNALL = new ErrorCode("FILE_TYPE_UNALL", "文件类型不全");// 比如传了两个身份证正面而缺少背面
  public static final ErrorCode FILE_FORMAT_ERROR = new ErrorCode("FILE_FORMAT_ERROR", "文件格式异常");
  public static final ErrorCode FILE_GET_ERROR = new ErrorCode("FILE_GET_ERROR", "文件获取异常");

  // * 信息检索错误
  public static final ErrorCode EMPTY_RECORD = new ErrorCode("EMPTY_RECORD", "不存在记录信息");
  public static final ErrorCode USER_INVALID = new ErrorCode("USER_INVALID", "当前用户无此权限");

  private final String errorCode;
  private final String errorMsg;

  private ErrorCode(String errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }
}
