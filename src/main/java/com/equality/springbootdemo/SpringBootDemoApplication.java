package com.equality.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@MapperScan("com.equality.springbootdemo.mapper") //mybatis包扫描
@ImportResource("classpath:spring/transaction.xml")//如果使用声明式事务，需要在pom中引用spring-aspects
@ServletComponentScan //过滤器
@EnableCaching //开启缓存  可以在入口这里添加或者是类似session的配置类，在类中@Configuration @EnableCaching ; 另外，由于前面已经配置好Redis，将存储到其中；属性文件中可配置可不配置
public class SpringBootDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
