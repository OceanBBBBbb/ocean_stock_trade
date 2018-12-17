package com.xiangbinwang.config;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.xiangbinwang.controller"))
        .paths(PathSelectors.any())
        .build()
        .globalOperationParameters(setHeaders());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("stock trade")
        .description("股票交易、沪深港股美股自由交易")
        .termsOfServiceUrl("10.0.30.162:8080")
        .contact("宾海洋")
        .version("1.0")
        .build();
  }

  private List<Parameter> setHeaders() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new ParameterBuilder().name("accessKey").description("访问秘钥")
        .modelRef(new ModelRef("string"))
        .parameterType("header")
        .required(true)
        .build()
    );
    parameters.add(new ParameterBuilder().name("sign").description("签名")
        .modelRef(new ModelRef("string"))
        .parameterType("header")
        .required(true)
        .build()
    );
    return parameters;
  }

//  // 适用类、接口（包括注解类型）或枚举
//  @Retention(RetentionPolicy.RUNTIME)
//  @Target(ElementType.TYPE)
//  public @interface ClassInfo {
//    String value();
//  }
//  // 适用field属性，也包括enum常量
//  @Retention(RetentionPolicy.RUNTIME)
//  @Target(ElementType.FIELD)
//  public @interface FieldInfo {
//    int[] value();
//  }
//  // 适用方法
//  @Retention(RetentionPolicy.RUNTIME)
//  @Target(ElementType.METHOD)
//  public @interface MethodInfo {
//    String name() default "long";
//    String data();
//    int age() default 27;
//  }

}

