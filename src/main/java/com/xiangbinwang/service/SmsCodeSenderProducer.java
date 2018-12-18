package com.xiangbinwang.service;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送，后面引入disruptor发
 */
@Component
public class SmsCodeSenderProducer {
  @Autowired
  private RabbitMessagingTemplate rabbitMessagingTemplate;

  public void sendSmsCodeRequest2RabbitMq(final String payload) {
    this.rabbitMessagingTemplate.convertAndSend("exchange.smscodesender", "queue.smscodesender", payload);
  }
}
