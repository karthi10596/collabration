package com.person.rest;

import java.util.Date;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.person.DAO.BlogPostDAO;
import com.person.DAO.UserDAO;
import com.person.entity.BlogComment;
import com.person.entity.BlogPost;
import com.person.entity.User;
import com.person.entity.error;


@RestController
public class BlogController {
	@Autowired(required=true)
	private BlogPostDAO blogdao;
		@Autowired
	private UserDAO userdao;
		
		
		
	//Adding blog post
		@RequestMapping(value="/saveblogpost",method=RequestMethod.POST)
		public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
			if(session.getAttribute("username")==null){
				error err=new error(5,"UnAuthorized User");
				return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
			}
			String username=(String)session.getAttribute("username");
			User user=userdao.getUserByUsername(username);
			blogPost.setPostedOn(new Date());
			blogPost.setPostedBy(user);
			try{
			blogdao.saveBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);//200 - 1st call back function will be called
			}catch(Exception e){
				error err=new error(6,"Unable to insert blog post details " + e.getMessage());
				return new ResponseEntity<error>(err,HttpStatus.INTERNAL_SERVER_ERROR);//500 - 2nd call back func will be executed
			}
		}
		
		
		
	//list of blogpost
		@RequestMapping(value="/getblogposts/{approved}")
		public ResponseEntity<?> getBlogPosts(@PathVariable int approved,HttpSession session){
			if(session.getAttribute("username")==null){
				error err=new error(5,"UnAuthorized User");
				return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
			}
			List<BlogPost> blogPosts=blogdao.getBlogPosts(approved);
			return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
		}
		
		
	//getting blog post details
		@RequestMapping(value="/getblogpostbyid/{id}",method=RequestMethod.GET)
		public ResponseEntity<?> getBlogPostById(@PathVariable int id,HttpSession session){
			if(session.getAttribute("username")==null){
				error err=new error(5,"UnAuthorized User");
				return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
			}
			BlogPost blogPost=blogdao.getBlogPostById(id);
			return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
		
		
	//Accepting or rejecting
		
		@RequestMapping(value="/updateblogpost",method=RequestMethod.PUT)
		public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
			if(session.getAttribute("username")==null){
				error err=new error(5,"UnAuthorized User");
				return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
			}
			try{
			blogdao.updateBlogPost(blogPost);
					return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
		catch(Exception e){
			error err=new error(6,"Unable to update blog post"+e.getMessage());
			return new ResponseEntity<error>(err,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
		
		
		//adding blog comment
		
		@RequestMapping(value="/addblogcomment",method=RequestMethod.POST)
		public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
			if(session.getAttribute("username")==null){
				error err=new error(5,"UnAuthorized User");
				return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
			}
			String username=(String)session.getAttribute("username");
			User user=userdao.getUserByUsername(username);
			blogComment.setCommentedBy(user);
			blogComment.setCommentedOn(new Date());
			try{
				blogdao.addBlogComment(blogComment);
				return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
			}
			
			catch(Exception e){
				error err=new error(7,"Unable to add blog comment"+e.getMessage());
				return new ResponseEntity<error>(err,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		
	//getting blog comment	
		@RequestMapping(value="/getblogcomments/{blogPostId}",method=RequestMethod.GET)
		public ResponseEntity<?> getBlogComments(@PathVariable int blogPostId,HttpSession session){
			if(session.getAttribute("username")==null){
				error err=new error(5,"UnAuthorized User");
				return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
			}
			List<BlogComment> blogComments=blogdao.getAllBlogComments(blogPostId);
			return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
			
		}

}
