package com.equality.springbootdemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	/**
	 * 设置起始页
	 * @return
	 */
	@RequestMapping(value = {"/index","/"}, method = {RequestMethod.GET})
	public String index() {

		return "login";
    }
	
	@RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
	public String sure() {
		String username = null;
		String password = null;
		System.out.println("username:"+username+";password:"+password);
        //return "welcome";
		return "redirect:welcome";
    }
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	    	SecurityContextLogoutHandler temp =	new SecurityContextLogoutHandler();
	    	temp.setClearAuthentication(true);
	    	temp.setInvalidateHttpSession(true);
	    	temp.logout(request, response, auth);
	    }
	    return "redirect:/index?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	
}
