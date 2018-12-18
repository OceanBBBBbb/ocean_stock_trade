package com.xiangbinwang.service;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.util.StringUtils;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费消息监听
 */
@Component
@Slf4j
public class SmsCodeSenderListener {
  //监听并消费队列queue.smscodesender
  @RabbitListener(queues = "queue.smscodesender")
  public void receiveSmsCodeSenderQueue(String payload) {
    Map<String, Object> map = JSON.parseObject(payload, Map.class);
    List<String> mobileNos = (List<String>) map.get("mobileNos");
    String smsCode = (String) map.get("smsCode");
    log.info("这条消息被我消费了：" + "mobileNos = " + StringUtils.join(mobileNos, ",") + ",smsCode = " + smsCode);
  }
}
