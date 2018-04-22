package com.equality.springbootdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.equality.springbootdemo.authentication.MyUserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
     * 配置user-detail服务
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth)throws Exception{
    	//默认的验证的方法 ProviderManager.authenticate → InMemoryUserDetailsManager.loadUserByUsername
    	
    	//1. 基于内存的用户存储、认证
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN","USER")
                .and()
                .withUser("user").password("user").roles("USER");
        
        //2. 设置另外1个UserDetailsService
        //auth.userDetailsService(myUserDetailsService);
        
        
    }

    /**
     * 拦截请求  配置如何通过拦截器保护请求
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http)throws Exception{
    	http    	
	    .formLogin() 
    		.loginPage("/index")  // 注意：这里的坑，登录页面不能 写/login    // .loginPage("/index")//指定自定义的登录页面  可参考文档5.3 Java Configuration and Form Login  
		    	//注意：如果写了 .loginProcessingUrl .successForwardUrl 不会进入到/login 中
		    	.loginProcessingUrl("/login")//form表单POST请求url提交地址，默认为/login    	    
		    	.successForwardUrl("/welcome")  //登录成功跳转地址
		    	.permitAll()
	    /*.and()
	    	.logout()
				.logoutUrl("/logout")    //登出路径
				.logoutSuccessUrl("/index")    //登出成功跳转到登陆页面
				.deleteCookies("SESSION")  //删除cookie
*/		.and()
			.authorizeRequests()
	    		.antMatchers("/images/**","/css/**","/js/**").permitAll()   //静态资源设置为任何人都可以访问    可参考文档5.4 Authorize Requests
	    	.anyRequest()
	    		.authenticated()
			;		
		
    	//http.csrf().disable();//禁用csrf,或者在前台中添加相关标签，注意html和jsp页面添加隐藏域稍有不同
    }

    /**
     * 拦截请求      配置Spring Security的Filter链
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
    
    
    
    
    /**
     * 第2部分
     */

    
    
    @Override
    protected UserDetailsService userDetailsService() {
    	
    	/*InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("neil").password("neil")
                .authorities("ROLE_USER","read_x").build());
        return manager;*/
    	
    	return super.userDetailsService();
    }
    
    
   /* @Autowired
    private MyUserDetailsService myUserDetailsService;//自定义用户服务
*/    
    
    

	
}
