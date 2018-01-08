package com.person.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Blog {
	@Id
	private int BlogID;
	String Blogname,BlogContent,Status;
	int Likes;
	public int getBlogID() {
		return BlogID;
	}
	public void setBlogID(int blogID) {
		BlogID = blogID;
	}
	public String getBlogname() {
		return Blogname;
	}
	public void setBlogname(String blogname) {
		Blogname = blogname;
	}
	public String getBlogContent() {
		return BlogContent;
	}
	public void setBlogContent(String blogContent) {
		BlogContent = blogContent;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getLikes() {
		return Likes;
	}
	public void setLikes(int likes) {
		Likes = likes;
	}
	
}
