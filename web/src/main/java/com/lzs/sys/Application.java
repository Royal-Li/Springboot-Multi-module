package com.lzs.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

//(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@ServletComponentScan
@EnableCaching  //开启缓存
public class Application {

	public static void main(String[] args) {
		System.out.println("hello Application模块");
		SpringApplication.run(Application.class, args);
		
	}
}
