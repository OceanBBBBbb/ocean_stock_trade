package com.xiangbinwang.util;

import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;

public class MainUtil {

  public static void main(String[] args) {
    System.out.println(getSkey("2c50d835ad02"));
//    System.out.println(uuidRandom());
  }

  // sha256加密
  private static String getSkey(String mingwen) {
    return DigestUtils.sha256Hex(mingwen).toUpperCase();
  }

  // 获取12位的随机uuid
  private static String uuidRandom(){
    String uuid = UUID.randomUUID().toString();
    return uuid.substring(uuid.length() - 12);
  }

}
