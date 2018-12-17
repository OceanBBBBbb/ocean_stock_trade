package com.xiangbinwang.interceptor;

import com.xiangbinwang.common.ErrorCode;
import com.xiangbinwang.common.Exception.TradeException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BrokerKey {

  private String brokerId;
  private String method;
  private String accessKey;
  private String secretKey;
  private Date createTime;
  private Date updateTime;

  public static BrokerKeyBuilder builder() {
    return new BrokerKeyBuilder();
  }

  public static class BrokerKeyBuilder {

    private String brokerId;
    private Method method;
    private String accessKey;
    private String secretKey;
    private Date createTime;
    private Date updateTime;

    public BrokerKey build() {
      return new BrokerKey(this.brokerId, this.method == null ? null : method.name(),
          this.accessKey, this.secretKey, this.createTime, this.updateTime);
    }

    public BrokerKeyBuilder updateTime(Date updateTime) {
      this.updateTime = updateTime;
      return this;
    }

    public BrokerKeyBuilder createTime(Date createTime) {
      this.createTime = createTime;
      return this;
    }

    public BrokerKeyBuilder secretKey(String secretKey) {
      this.secretKey = secretKey;
      return this;
    }

    public BrokerKeyBuilder accessKey(String accessKey) {
      this.accessKey = accessKey;
      return this;
    }

    public BrokerKeyBuilder method(Method method) {
      this.method = method;
      return this;
    }

    public BrokerKeyBuilder brokerId(String brokerId) {
      this.brokerId = brokerId;
      return this;
    }
  }

  public enum Method {
    MD5, SHA256;

    private static final Map<String, Method> stringToEnum = new HashMap<>();

    static {
      for (Method rs : values()) {
        stringToEnum.put(rs.name(), rs);
      }
    }

    public static Method getOrNull(String name) {
      return Optional.ofNullable(stringToEnum.get(name)).orElse(null);
    }

    public static Method get(String name) {
      return Optional.ofNullable(stringToEnum.get(name)).orElseThrow((() -> new TradeException(ErrorCode.AUTH_UNSUPT)));
    }
  }
}
