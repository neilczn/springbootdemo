package com.equality.springbootdemo.authentication;

import java.util.ArrayList;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 自定义的UserDetailsService   spring security框架默认使用的是UserDetailsServiceDelegator
 * @author asus
 *
 */
//@Component
public class MyUserDetailsService implements UserDetailsService {

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//1. 开始		
		//这部分的实现可参考 InMemoryUserDetailsManager.loadUserByUsername
		
		User detail = null;
        //判断username是否为null
        if(username != null){
            ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_MODELER"));
            authorities.add(new SimpleGrantedAuthority("ROLE_ANALYST"));

            //可以加入其他判断逻辑，以及根据username获取密码的方法。
            //由于是Demo，就直接将密码写死为"test"，权限直接设置成"ROLE_ADMIN"、"ROLE_MODELER"和"ROLE_ANALYST"
            detail = new User(username, "test", authorities);
        }

        return  detail;
		
		//TODO:从数据查询
		
		//1. 结束
	}

}
