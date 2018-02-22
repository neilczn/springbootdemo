package com.equality.springbootdemo;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

	@Autowired
    private WebApplicationContext context;
	
	@Autowired
    private ApplicationContext ac;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Test
	public void contextLoads() {
		String[] beanNames =  context.getBeanDefinitionNames();
        System.out.println("beanNames个数："+beanNames.length);
        for(String bn:beanNames){
            System.out.println(bn);
        }
        System.out.println("==================");
        String[] beanNames2 =  ac.getBeanDefinitionNames();
        System.out.println("beanNames2个数："+beanNames2.length);
        for(String bn:beanNames2){
            System.out.println(bn);
        }
	}
	
	
	@Test
	public void jdbcTest() {
		DataSource dataSource = (DataSource)ac.getBean("dataSource");
		JdbcTemplate jdbcTemplate2 = new JdbcTemplate(dataSource);
		String title = jdbcTemplate.queryForObject("select title from blog where id =1", String.class);
		System.out.println(title);
		String title2 = jdbcTemplate.queryForObject("select title from blog where id =1", String.class);
		System.out.println(title2);
	}
	
	@Test
	public void jdbcUpdateTest() {		
		String title = jdbcTemplate.queryForObject("select title from blog where id =1", String.class);
		System.out.println(title);
		title +="1";
		int i = jdbcTemplate.update("update blog set title='"+title+"' where id =1");
		System.out.println(i);
	}
	
	/**
	 * 回滚
	 */
	@Test
	@Transactional
    @Rollback
	public void jdbcUpdateTest2() {		
		String title = jdbcTemplate.queryForObject("select title from blog where id =1", String.class);
		System.out.println(title);
		title +="1234";
		int i = jdbcTemplate.update("update blog set title='"+title+"' where id =1");
		System.out.println(i);
	}

}
