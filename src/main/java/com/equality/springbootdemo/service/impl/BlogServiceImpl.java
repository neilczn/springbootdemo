/**
 * 
 */
package com.equality.springbootdemo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.equality.springbootdemo.entity.Blog;
import com.equality.springbootdemo.mapper.BlogMapper;
import com.equality.springbootdemo.service.BlogService;



/**
 * @author asus
 *
 */
@Service
public class BlogServiceImpl implements BlogService {
	
	private BlogMapper blogMapper;
		
	/**
	 * @param blogMapper the blogMapper to set
	 */
	@Autowired
	public void setBlogMapper(BlogMapper blogMapper) {
		this.blogMapper = blogMapper;
	}

	/**
	 * 
	 */
	public BlogServiceImpl() {
	}

	/* 
	 * 如果使用声明式事务，需要在@ImportResource("classpath:spring/transaction.xml")，需要在pom中引用spring-aspects
	 */
	 //@Transactional
	public void add(Blog blog) {
		this.blogMapper.add(blog);
		//int a = 1/0;//测试事务
	}

	public Blog findById(Integer id) {
		Logger logger  = LoggerFactory.getLogger(this.getClass());
		logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
		return this.blogMapper.findById(id);
	}

	@Override
	public List<Blog> querylist(Integer greaterThanId) {
		return this.blogMapper.querylist(greaterThanId);
	}

}
