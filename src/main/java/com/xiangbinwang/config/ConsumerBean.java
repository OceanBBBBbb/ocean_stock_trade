package com.xiangbinwang.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * 消息消费，这个之后分离到另一个系统
 */
@Configuration
@EnableRabbit
public class ConsumerBean implements RabbitListenerConfigurer {
  @Bean
  public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
    DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
    factory.setMessageConverter(new MappingJackson2MessageConverter());
    return factory;
  }

  //queue listener 观察 监听模式 当有消息到达时会通知监听在对应的队列上的监听对象
  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
      ConnectionFactory connectionFactory) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    // factory.setPrefetchCount(5);//指定一个请求能处理多少个消息，如果有事务的话，必须大于等于transaction数量.
    factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
    //MANUAL：将ACK修改为手动确认，避免消息在处理过程中发生异常造成被误认为已经成功消费的假象。
    //factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    return factory;
  }

  @Override
  public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
    registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
  }
}
