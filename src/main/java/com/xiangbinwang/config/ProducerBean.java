package com.xiangbinwang.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class ProducerBean {

  //rabbitAdmin 用于管理 exchanges, queues and bindings等
  @Bean
  RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
    return new RabbitAdmin(connectionFactory);
  }

  //queue 声明队列：queue.smscodesender
  @Bean
  Queue queueSmsCodeSender(RabbitAdmin rabbitAdmin) {
    Queue queue = new Queue("queue.smscodesender", true);
    rabbitAdmin.declareQueue(queue);
    return queue;
  }

  //exchange 声明交换：exchange.smscodesender
  @Bean
  TopicExchange exchange(RabbitAdmin rabbitAdmin) {
    TopicExchange topicExchange = new TopicExchange("exchange.smscodesender");
    rabbitAdmin.declareExchange(topicExchange);
    return topicExchange;
  }

  //绑定exchange和queue
  @Bean
  Binding bindingExchangeSmsCodeSender(Queue queueSmsCodeSender, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
    Binding binding = BindingBuilder.bind(queueSmsCodeSender).to(exchange).with("queue.smscodesender");
    rabbitAdmin.declareBinding(binding);
    return binding;
  }

  //声明 spring template
  @Bean
  public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
    RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
    rabbitMessagingTemplate.setMessageConverter(jackson2Converter());//配置json转换器
    rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
    return rabbitMessagingTemplate;
  }

  //消息对象json转换类
  @Bean
  public MappingJackson2MessageConverter jackson2Converter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    return converter;
  }
}
