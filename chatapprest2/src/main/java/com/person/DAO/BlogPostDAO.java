package com.person.DAO;

import java.util.List;

import com.person.entity.BlogComment;
import com.person.entity.BlogPost;



public interface BlogPostDAO {
	void saveBlogPost(BlogPost blogPost);

	List<BlogPost> getBlogPosts(int approved);

	BlogPost getBlogPostById(int id);
	void updateBlogPost(BlogPost blogpost);
	void addBlogComment(BlogComment blogComment);
	List<BlogComment> getAllBlogComments(int blogPostId);
}
