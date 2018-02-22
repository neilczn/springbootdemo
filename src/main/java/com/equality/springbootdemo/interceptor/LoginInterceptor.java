/**
 * 
 */
package com.equality.springbootdemo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author asus
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * 
	 */
	public LoginInterceptor() {
		System.out.println("LoginInterceptor()");
	}

	/* 
	 * 
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex)
			throws Exception {
		System.out.println("LoginInterceptor-------------------afterCompletion");
	}

	/* 
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView arg3)
			throws Exception {
		System.out.println("LoginInterceptor-------------------postHandle");
	}

	/* 
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		boolean flag =true;
        /*User user=(User)request.getSession().getAttribute("user");
        if(null==user){
            response.sendRedirect("toLogin");
            flag = false;
        }else{
            flag = true;
        }*/
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		request.setAttribute("basePath",basePath); 
		System.out.println("LoginInterceptor-------------------preHandle");
        return flag;
	}

}
