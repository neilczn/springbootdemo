/**
 * 
 */
package com.equality.springbootdemo.mapper;

import java.util.List;

import com.equality.springbootdemo.entity.BaseEntity;



/**
 * @author asus
 *
 */
public interface BaseMapper<T extends BaseEntity> {

	public T findById(Integer id);
	
	public List<T> querylist(Integer greaterThanId);

	public void add(T t);
	
}
