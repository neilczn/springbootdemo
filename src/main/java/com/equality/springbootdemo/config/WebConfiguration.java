package com.equality.springbootdemo.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
	
	//注意输入的路径
	//http://localhost:8080/welcome?lang=zh_cn
	//http://localhost:8080/welcome?lang=en_us
	
	@Bean  
    public LocaleChangeInterceptor localeChangeInterceptor() {  
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");  
        return lci;  
    }  
	
	@Bean  
    public LocaleResolver localeResolver() {  
        CookieLocaleResolver cl = new CookieLocaleResolver();  
        //cl.setDefaultLocale(Locale.ENGLISH);//未起作用
        cl.setCookieName("lang");  
        return cl;  
    }  

    @Override  
    public void addInterceptors(InterceptorRegistry registry) {  
        registry.addInterceptor(localeChangeInterceptor());  
    }  
}
