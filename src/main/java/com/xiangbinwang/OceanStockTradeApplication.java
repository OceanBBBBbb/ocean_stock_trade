package com.xiangbinwang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.xiangbinwang.dao.mappers")
@ServletComponentScan
public class OceanStockTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OceanStockTradeApplication.class, args);
	}

}

