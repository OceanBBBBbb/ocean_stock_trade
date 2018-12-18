package com.xiangbinwang.controller;


import com.xiangbinwang.common.ApiResponse;
import com.xiangbinwang.common.Bean.TradeOrder;
import com.xiangbinwang.common.Bean.TradeResp.OrderResp;
import com.xiangbinwang.common.ErrorCode;
import com.xiangbinwang.common.Exception.TradeException;
import com.xiangbinwang.interceptor.AuthRequired;
import com.xiangbinwang.service.TradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api("交易接口")
@Slf4j
@RestController
@RequestMapping("/v1/api/trade")
public class TradeController {

  @Autowired
  private TradeService tradeService;

  @ApiOperation(value="测试", notes="这是一个测试")
  @GetMapping("/test")
  @AuthRequired
  public ApiResponse<String> test(){
    try {
      return new ApiResponse<>("测试成功，你可使用了");
    } catch (TradeException e) {
      log.error("获取文件异常：",e);
      return new ApiResponse<>(ErrorCode.FILE_GET_ERROR.getErrorCode(),ErrorCode.FILE_GET_ERROR.getErrorMsg());
    }
  }

  @ApiOperation(value="下单", notes="向系统挂一个订单")
  @PostMapping("/trdOrder")
  @AuthRequired
  public ApiResponse<OrderResp> trdOrder(@RequestBody TradeOrder tradeOrder){
    OrderResp orderResp = tradeService.trdOrder(tradeOrder);
    return new ApiResponse<>(orderResp);
  }
}
