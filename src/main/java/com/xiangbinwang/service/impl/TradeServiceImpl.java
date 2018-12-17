package com.xiangbinwang.service.impl;

import com.xiangbinwang.common.Bean.TradeOrder;
import com.xiangbinwang.common.Bean.TradeResp.OrderResp;
import com.xiangbinwang.dao.entity.OrderOuter;
import com.xiangbinwang.dao.mappers.OrderOuterMapper;
import com.xiangbinwang.service.TradeService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService {

  @Autowired
  private OrderOuterMapper orderOuterMapper;

  @Override
  public OrderResp trdOrder(TradeOrder tradeOrder) {
    OrderOuter orderOuter = buildOrder(tradeOrder);
    // 调用外部证券所api下单，获取订单ID
    orderOuter.setOrderId(new Date().getTime());
    // 持久化
    orderOuterMapper.insert(orderOuter);
    return new OrderResp(String.valueOf(orderOuter.getOrderId()),"");
  }

  private OrderOuter buildOrder(TradeOrder tradeOrder) {
    OrderOuter orderOuter = new OrderOuter();
    orderOuter.setCode(tradeOrder.getCode());
    orderOuter.setOrderType(tradeOrder.getOrderType().getNumber());
    orderOuter.setSecMarket(tradeOrder.getTrdMarket().getNumber());
    orderOuter.setTrdSide(tradeOrder.getTrdSide().getNumber());
    orderOuter.setPrice(tradeOrder.getPrice());
    orderOuter.setQty(tradeOrder.getQty());
    orderOuter.setOrderStatus(1);
    return orderOuter;
  }
}
