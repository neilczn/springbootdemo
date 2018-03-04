package com.equality.springbootdemo.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;

/*@Order(100)
@WebFilter(filterName = "encodingFilter", urlPatterns = "/*")*/
public class EncodingFilter implements Filter {

	public EncodingFilter() {
		System.out.println("EncodingFilter()");
	}

	@Override
	public void destroy() {
		System.out.println("EncodingFilter   destroy()");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("EncodingFilter   doFilter()");
		
		// 1.强制转换  
        HttpServletRequest request = (HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) resp;  
  
        // 2.操作  
        HttpServletRequest myrequest = new MyRequest(request); // 增强后的request,解决了编码问题  
        response.setContentType("text/html;charset=utf-8");  
  
        // 3.放行  
        chain.doFilter(myrequest, response); 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("EncodingFilter   init()");
	}

}

//装饰类  
class MyRequest extends HttpServletRequestWrapper {  

 private HttpServletRequest request;  

 public MyRequest(HttpServletRequest request) {  
     super(request);  
     this.request = request;  
 }  

 // 重写关于获取请求参数的方法.  
 @Override  
 public String getParameter(String name) {  
     Map<String, String[]> map = getParameterMap();  

     if (name == null) {  
         return null;  
     }  
     String[] st = map.get(name);  
     if (st == null || st.length == 0) {  
         return null;  
     }  

     return st[0];  
 }  

 @Override  
 public String[] getParameterValues(String name) {  
     Map<String, String[]> map = getParameterMap();  

     if (name == null) {  
         return null;  
     }  
     String[] st = map.get(name);  

     return st;  

 }  

 private boolean flag = true;  

 @Override  
 public Map getParameterMap() {  
     // 1.得到所有请求参数的Map集合  
     Map<String, String[]> map = request.getParameterMap(); // 有编码问题.  

	   //判断请求方式
	String method = request.getMethod();
	String encoding = request.getCharacterEncoding();
     
     // 2.解决编码问题.  
     if (flag && "get".equalsIgnoreCase(method)) {  
         for (String key : map.keySet()) {  
             String[] values = map.get(key);  

             for (int i = 0; i < values.length; i++) {  
            	 if (values[i] != null && !"utf-8".equalsIgnoreCase(encoding)) {
            		//判断是不是GBK编码 即是否乱码
            		/* if(!(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(values[i]))){  
            		        
            		}   */  
            		 try { 
            			 //比如从iso8859-1转成utf-8
     		        	values[i] = new String(values[i].getBytes(encoding),  
                                 "utf-8");
     		        } catch (UnsupportedEncodingException e) {  
     		            e.printStackTrace();  
     		        }     
				}        
             } 
         }  
         flag = false;  
     }  
     return map;  
 }  

}  
