package com.jinhe.juhe.livejuhe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc//启动MVC
@EnableAutoConfiguration
@SpringBootApplication//SpringBoot启动核心
public class LivejuheApplication {


	public static void main(String[] args) throws Exception {
		SpringApplication.run(LivejuheApplication.class, args);
	}
}