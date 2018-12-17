package com.xiangbinwang.interceptor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

@Slf4j
public class AuthServletRequestWrapper extends HttpServletRequestWrapper {

  private byte[] body;

  public AuthServletRequestWrapper(HttpServletRequest request) {

    super(request);

    StringBuilder builder = new StringBuilder();
    builder.append("接收到的请求为：").append(request.getMethod()).append(" ").append(request.getRequestURI()).append(" ").append(request.getProtocol()).append("\n");
    for (Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements();) {
      String element = e.nextElement();
      builder.append(element).append(": ").append(request.getHeader(element)).append("\n");
    }
    //缓存请求body
    try {
      body = StreamUtils.copyToByteArray(request.getInputStream());
      builder.append(getBody());
    } catch (IOException e) {
      throw new RuntimeException("读取请求数据异常", e);
    }
    log.debug(builder.toString());
  }

  /**
   * 重写 getInputStream()
   */
  @Override
  public ServletInputStream getInputStream() {
    if(body == null || body.length == 0){
      body= "{}".getBytes(Charset.forName("UTF-8"));
    }
    return new CustomServletInputStream(body);
  }

  /**
   * 重写 getReader()
   */
  @Override
  public BufferedReader getReader() {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  //Use this method to read the request body N times
  String getBody() {
    return new String(body, Charset.forName("UTF-8"));
  }


  private class CustomServletInputStream extends ServletInputStream {

    private final ByteArrayInputStream buffer;

    CustomServletInputStream(byte[] body) {
      this.buffer = new ByteArrayInputStream(body);
    }

    @Override
    public int read() {
      return buffer.read();
    }

    @Override
    public boolean isFinished() {
      return buffer.available() == 0;
    }

    @Override
    public boolean isReady() {
      return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {
      throw new RuntimeException("Not implemented");
    }
  }

}