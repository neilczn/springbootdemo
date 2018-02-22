package com.equality.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication// same as @Configuration @EnableAutoConfiguration @ComponentScan
@MapperScan("com.equality.springbootdemo.mapper")
@ImportResource("classpath:spring/transaction.xml")//如果使用声明式事务，需要在pom中引用spring-aspects
@ServletComponentScan
public class SpringBootDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
