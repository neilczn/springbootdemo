/**
 * 
 */
package com.equality.springbootdemo.entity;

import java.io.Serializable;

/**
 * @author asus
 *
 */
public class Blog extends BaseEntity implements Serializable  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public Blog() {}

	public Blog(Integer id,String title, Integer authorId) {
		super.setId(id);
		this.title = title;
		this.authorId = authorId;
	}

	//private Integer id;
	private String title;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	private Integer authorId;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 
	@Override
	public String toString() {
		return "Blog [id=" + super.getId() + ",title=" + title + ", authorId=" + authorId + "]";
	}*/
	
	
}
