package com.xiangbinwang.common.Bean;

import com.xiangbinwang.common.TrdCommon.OrderType;
import com.xiangbinwang.common.TrdCommon.TrdMarket;
import com.xiangbinwang.common.TrdCommon.TrdSide;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TradeOrder {

  private TrdMarket trdMarket;
  private TrdSide trdSide;
  private OrderType orderType;
  private String code;
  private double qty;
  private double price;

}
