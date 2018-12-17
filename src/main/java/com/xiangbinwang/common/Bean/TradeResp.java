package com.xiangbinwang.common.Bean;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

public class TradeResp {

  /**
   * 下单后的返回
   */
  @Data
  @ToString
  public static class OrderResp{
    private String orderId;
    private String msg;

    public OrderResp(String orderId,String msg){
      this.orderId = orderId;
      this.msg = msg;
    }
  }


}
