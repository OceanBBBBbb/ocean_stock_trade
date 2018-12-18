package com.xiangbinwang.controller;

import com.xiangbinwang.common.ApiResponse;
import com.xiangbinwang.common.ErrorCode;
import com.xiangbinwang.common.Exception.TradeException;
import com.xiangbinwang.interceptor.AuthRequired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("行情接口")
@Slf4j
@RestController
@RequestMapping("/v1/api/quot")
public class QuotController {

  @ApiOperation(value="获取盘面", notes="获取买卖1价")
  @GetMapping("/getTicker")
  public ApiResponse<String> getTicker(){
    try {
      return new ApiResponse<>("getTicker");
    } catch (TradeException e) {
      log.error("获取文件异常：",e);
      return new ApiResponse<>(ErrorCode.FILE_GET_ERROR.getErrorCode(),ErrorCode.FILE_GET_ERROR.getErrorMsg());
    }
  }
}
