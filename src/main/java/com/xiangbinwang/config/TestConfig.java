package com.xiangbinwang.config;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import com.xiangbinwang.dao.entity.UserKey;
import com.xiangbinwang.dao.mappers.UserKeyMapper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
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

  @Override
  public void run(ApplicationArguments args) throws Exception {
    testDb();
//    testInsertlong();
//    testMongo();
  }


  public void testDb() {
    List<UserKey> userKeys = userKeyMapper.selectAll();
    System.out.println(Arrays.toString(userKeys.toArray()));
  }
}
