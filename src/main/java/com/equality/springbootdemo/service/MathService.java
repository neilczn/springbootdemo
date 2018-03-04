package com.equality.springbootdemo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class MathService {

	/**
	 * 取反
	 * @param i
	 * @return
	 */
	@Cacheable("negation") //
	public int negation(int i) {
		System.out.println("input:" + i + ";output:"+(-i));
	    return -i;
	}
	
	/**
	 * 删除取反--手动触发，直接在radis是删除不了的
	 * @param i
	 * @return
	 */
	@CacheEvict(cacheNames = "negation")
	public String negationEvict(int id) {
		return "success";
	}
	
}
