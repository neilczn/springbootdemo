package com.equality.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	public HomeController() {
	}
	
	@RequestMapping("/home")
    @ResponseBody
    String index() {
        return "Hello World!";
    }
	
	@RequestMapping("/welcome")
    public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        modelAndView.addObject("message","Welcome to Spring Boot & Thymeleaf");
        return modelAndView;
    }
	
	

}
