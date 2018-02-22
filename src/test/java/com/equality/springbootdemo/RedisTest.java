package com.equality.springbootdemo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.equality.springbootdemo.entity.Blog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

	public RedisTest() {
	}
	
	@Test
	public void test1(){
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Server is running: "+jedis.ping());
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
        //Lists
        jedis.lpush("forezp-list", "Redis");
        jedis.lpush("forezp-list", "Mongodb");
        jedis.lpush("forezp-list", "Mysql");
        // Get the stored data and print it
        List<String> list = jedis.lrange("forezp-list", 0 ,5);
        for(int i=0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }
	}
	
	@Test
	public void test2(){
		Jedis jedis = new Jedis("localhost");
		Blog blog = new Blog(10,"test10",6);
		ObjectMapper mapper = new ObjectMapper();
		// 序列化成字符串
	    String json = null;
		try {
			json = mapper.writeValueAsString(blog);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    System.out.println(json);
	    jedis.set("jsonkey", json);
	    String json2 = jedis.get("jsonkey");
	    try {
			Blog blog2 = mapper.readValue(json2, Blog.class);
			System.out.println(blog2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	}
	
	@Autowired
	private StringRedisTemplate template;
	
	@Test
	public void test3(){
		ValueOperations<String, String> ops = this.template.opsForValue();
		ops.set("age", "11", 1, TimeUnit.MINUTES);
		String age = ops.get("age");
		System.out.println(age);
	}
	

}
