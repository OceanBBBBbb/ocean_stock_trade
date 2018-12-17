package com.xiangbinwang.service;

import com.xiangbinwang.common.Bean.TradeOrder;
import com.xiangbinwang.common.Bean.TradeResp.OrderResp;

public interface TradeService {

  OrderResp trdOrder(TradeOrder tradeOrder);
}
