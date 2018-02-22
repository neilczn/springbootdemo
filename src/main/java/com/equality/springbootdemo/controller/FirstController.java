/**
 * 
 */
package com.equality.springbootdemo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author asus
 *
 */
@RestController
@RequestMapping("/first")
public class FirstController {

	/**
	 * 
	 */
	public FirstController() {
	}
	
	@RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
	
	@RequestMapping("/index")
    public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }
	
	@RequestMapping("/submit")
    public String SubmitParams(String name) {
        return name;
    }
	
	@RequestMapping("/set")
	public String set(HttpServletRequest req) {
        req.getSession().setAttribute("testKey", "testValue123");
        return "设置session:testKey=testValue";
    }

    @RequestMapping("/query")
    public String query(HttpServletRequest req) {
        Object value = req.getSession().getAttribute("testKey");
        return "查询Session：\"testKey\"=" + value;
    }
	

}
