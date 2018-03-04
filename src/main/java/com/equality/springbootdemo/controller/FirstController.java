/**
 * 
 */
package com.equality.springbootdemo.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	public String set(HttpServletRequest req,HttpServletResponse response) {
        req.getSession().setAttribute("testKey", "testValue123"); 
        
        String id = UUID.randomUUID().toString();//生成一个随机字符串    	
    	String tempId ="id" + id.substring(0, 4) + "";
		Cookie cookie = new Cookie(tempId, id);//创建Cookie对象，指定名字和值	
		//Q:如果设置了 path domain 这个cookie提交不到服务器，如果path=/同时会产生新的sessionid
		/*cookie.setPath("/com.equality");
		cookie.setDomain("com.equality");*/
		response.addCookie(cookie);//在响应中添加Cookie对象     
        
        return "设置session:testKey=testValue123<br/>sessionid:"+req.getRequestedSessionId()+"<br/><a href='/first/get'>get</a>";
    }

    @RequestMapping("/get")
    public String get(HttpServletRequest req,HttpServletResponse response) {
    	Object value = req.getSession().getAttribute("testKey");
    	StringBuilder sb = new StringBuilder();
    	Cookie[] cs = req.getCookies();//获取请求中的Cookie
		if(cs != null) {//如果请求中存在Cookie
			for(Cookie cookie : cs) {//遍历所有Cookie
				sb.append(cookie.getName()+"：" + cookie.getValue()+":"+cookie.getDomain()+":"+cookie.getPath()+"<br/>");
			}
		}
        return "查询Session：\"testKey\"=" + value+";sessionid:"+req.getRequestedSessionId()+"<br/>"+sb.toString();
    }
    
    @RequestMapping("/setCookie")
	public String setCookie(HttpServletRequest req,HttpServletResponse response) {
    	
    	StringBuilder sb = new StringBuilder();
    	Cookie[] cs = req.getCookies();//获取请求中的Cookie
		if(cs != null) {//如果请求中存在Cookie
			for(Cookie cookie : cs) {//遍历所有Cookie
				cookie.getPath();
				sb.append(cookie.getName()+"：" + cookie.getValue()+":"+cookie.getDomain()+":"+cookie.getPath()+"<br/>");
			}
		}
    	
    	String id = UUID.randomUUID().toString();//生成一个随机字符串    	
    	String tempId ="id" + id.substring(0, 4) + "";
		Cookie cookie = new Cookie(tempId, id);//创建Cookie对象，指定名字和值	
		
		/*
		 * cookie.setPath("/asdfg/");
		cookie.setDomain("com.equality");*/
		response.addCookie(cookie);//在响应中添加Cookie对象        
        return "设置Cookie--"+cookie.getName()+":"+cookie.getValue()+":"+cookie.getDomain()+":"+cookie.getPath()+"<br/>"+
        sb.toString()+"<br/><a href='/first/getCookie'>getcookie</a>";
    }
    
    @RequestMapping("/getCookie")
    public String getCookie(HttpServletRequest req,HttpServletResponse response) {
    	StringBuilder sb = new StringBuilder();
    	Cookie[] cs = req.getCookies();//获取请求中的Cookie
		if(cs != null) {//如果请求中存在Cookie
			for(Cookie cookie : cs) {//遍历所有Cookie
				sb.append(cookie.getName()+"：" + cookie.getValue()+":"+cookie.getDomain()+":"+cookie.getPath()+"<br/>");
			}
		}
    	return "Cookie--"+sb.toString();
    }
    
	
	/*@RequestMapping("/set")
	public String set(HttpSession session) {
		session.setAttribute("testKey", "testValue123");
        return "设置session:testKey=testValue";
    }

    @RequestMapping("/query")
    public String query(HttpSession session) {
        Object value = session.getAttribute("testKey");
        return "查询Session：\"testKey\"=" + value+";sessionid:"+session.getId();
    }*/
	

}
