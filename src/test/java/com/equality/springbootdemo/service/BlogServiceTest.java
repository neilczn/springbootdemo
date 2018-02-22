package com.equality.springbootdemo.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.equality.springbootdemo.entity.Blog;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceTest {
	
	@Autowired
	private BlogService blogService;

	@Test
	public void testAdd() {
		Blog blog = new Blog(11,"test11",6);
		blogService.add(blog);
	}

	@Test
	public void testFindById() {
		blogService.findById(1);
	}

	@Test
	public void testQuerylist() {
		
	}

}
