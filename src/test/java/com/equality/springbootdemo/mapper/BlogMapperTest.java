package com.equality.springbootdemo.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.equality.springbootdemo.entity.Blog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogMapperTest {
	
	/**
	 * 注意需要在程序入口函数处@MapperScan
	 */
	@Autowired
	private BlogMapper blogMapper;

	@Before
	public void setUp() throws Exception {
		System.out.println(blogMapper);
	}

	@Test
	public void testFindById() {
	  Blog blog = blogMapper.findById(1);
	  System.out.println(blog);
	}

	/**
	 * 分页插件查询
	 */
	@Test
	public void testQuerylist() {
		PageHelper.startPage(2, 3);
		List<Blog> list2 =  blogMapper.querylist(0);
		for (Blog blog : list2) {
			System.out.println(blog);
		}
		//用PageInfo对结果进行包装
		PageInfo page = new PageInfo(list2);
		System.out.println(page);
	}

	@Test
	@Transactional
    @Rollback
	public void testAdd() {
		Blog blog = new Blog(10,"test10",6);
		blogMapper.add(blog);
	}

}
