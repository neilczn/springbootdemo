/**
 * 
 */
package com.equality.springbootdemo.service;

import java.util.List;

import com.equality.springbootdemo.entity.Blog;



/**
 * @author asus
 *
 */
public interface BlogService {

  void add(Blog blog);
  
  Blog findById(Integer id);
  
  List<Blog> querylist(Integer greaterThanId);
  
}
