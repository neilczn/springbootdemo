package com.equality.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equality.springbootdemo.service.MathService;

@RestController
@RequestMapping("/cache")
public class CacheController {

	@Autowired
	private MathService mathService;
	
	
	@RequestMapping("/not")
	public String negation(int i){
		return mathService.negation(i)+"";
	}
	
	@RequestMapping("/delnot")
	public String delNegation(int i){
		return mathService.negationEvict(i);
	}
	
}
