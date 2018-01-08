package com.person.DAO;

import java.util.List;

import com.person.entity.Blog;
import com.person.entity.User;



public interface BlogDAO {
	public void addBlog(Blog blog);
	public List<Blog> getList();
	public void deleteblog(Blog blog);
	public Blog getBlog(int BlogID);
	public void updateBlog(Blog blog);
	public boolean approveblog(Blog blog);
}
