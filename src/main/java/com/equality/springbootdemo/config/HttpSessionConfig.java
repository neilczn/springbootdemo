package com.equality.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

//@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=7200)
public class HttpSessionConfig {

	public HttpSessionConfig() {
		System.out.println("HttpSessionConfig()");
	}
	
	/**
	 * 注入CookieSerializer--设置path,针对sessionid
	 */
	@Bean  
    public CookieHttpSessionStrategy cookieHttpSessionStrategy() {  
        CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();  
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        //设置path后有问题
        //defaultCookieSerializer.setDomainName("localhost");
        //defaultCookieSerializer.setCookieName("SESSIONID");
        defaultCookieSerializer.setCookiePath("/");//  /first
        strategy.setCookieSerializer(defaultCookieSerializer);  
        return strategy;  
    }  
	
	/*@Bean(name = "cookieSerializer")  
    public CookieSerializer cookieSerializer() {  
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();  
        // Cookie名称  
        cookieSerializer.setCookieName(new StringBuilder("spring-boot-demo").append("SESSION").toString());  
        // HttpOnly  
        cookieSerializer.setUseHttpOnlyCookie(true);  
        // HTTPS定义  
        cookieSerializer.setUseSecureCookie(true);  
        *//** 
         * 解决子域问题：把cookiePath的返回值设置为统一的根路径就能让session id从根域获取， 
         * 这样同根下的所有web应用就可以轻松实现单点登录共享session 
         *//*  
        cookieSerializer.setCookiePath("/");  
        return cookieSerializer;  
    } */
	
	

}
