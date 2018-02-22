package com.equality.springbootdemo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.equality.springbootdemo.interceptor.LoginInterceptor;

@Component
public class Configuration extends WebMvcConfigurerAdapter {

	public Configuration() {
		System.out.println("Configuration()");
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {		
		//super.addInterceptors(registry);
		// 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(new LoginInterceptor());
        // 配置拦截的路径
        ir.addPathPatterns("/**");
        // 配置不拦截的路径
        ir.excludePathPatterns("/**.html");

        // 还可以在这里注册其它的拦截器
        //registry.addInterceptor(new OtherInterceptor()).addPathPatterns("/**");


	}

}
