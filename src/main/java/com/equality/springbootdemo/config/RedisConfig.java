package com.equality.springbootdemo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes")RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //cacheManager.setDefaultExpiration(15);//设置全局的具体的缓存时间，单位秒；不设置则一直存储
        
        // 设置缓存的过期时间,给不同名称的缓存设置不同的缓存时间
        Map<String, Long> expires = new HashMap<>();
        expires.put("negation", (long)15);
        expires.put("plus", (long)30);
        cacheManager.setExpires(expires);
        
        return cacheManager;
    }


	
}
