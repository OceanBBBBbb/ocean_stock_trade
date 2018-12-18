package com.xiangbinwang.config;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import com.xiangbinwang.dao.entity.UserKey;
import com.xiangbinwang.dao.mappers.UserKeyMapper;
import com.xiangbinwang.service.SmsCodeSenderProducer;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamSource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class TestConfig implements ApplicationRunner {

  @Autowired
  private UserKeyMapper userKeyMapper;

  @Autowired
  private SmsCodeSenderProducer senderService;


  @Override
  public void run(ApplicationArguments args) throws Exception {
    testRMQ();
//    testDb();
//    testInsertlong();
//    testMongo();
  }

  private void testRMQ() {
    Map<String, Object> msg = new HashMap<>();
    msg.put("mobileNos", Arrays.asList("13811111111", "13822222222"));
    msg.put("smsCode", "8888");
    senderService.sendSmsCodeRequest2RabbitMq(JSON.toJSONString(msg));
  }


  public void testDb() {
    List<UserKey> userKeys = userKeyMapper.selectAll();
    System.out.println(Arrays.toString(userKeys.toArray()));
  }
}
