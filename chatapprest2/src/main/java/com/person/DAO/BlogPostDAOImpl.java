package com.person.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.person.entity.BlogComment;
import com.person.entity.BlogPost;


@Repository
@Transactional
public class BlogPostDAOImpl implements BlogPostDAO {
	@Autowired
	SessionFactory sessionfactory;
		public void saveBlogPost(BlogPost blogPost) {
			Session session=sessionfactory.getCurrentSession();
			session.save(blogPost);

		}
		public List<BlogPost> getBlogPosts(int approved) {
			Session session=sessionfactory.getCurrentSession();
			//if approved method parameter is 0 => select * from blogpost_batch5 where approved=0;[waiting for approval]
			//if approved parameter is 1 => select * from blogpost_batch5 where approved=1;[approved blog posts]
			Query query=session.createQuery("from BlogPost where approved="+approved);
			return query.list();
		}
		public BlogPost getBlogPostById(int id) {
			Session session=sessionfactory.getCurrentSession();
			BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
			return blogPost;
		}
		
		public void updateBlogPost(BlogPost blogpost) {
			// TODO Auto-generated method stub
			Session session=sessionfactory.getCurrentSession();
			session.update(blogpost);
		}
		
		public void addBlogComment(BlogComment blogComment) {
			// TODO Auto-generated method stub
			Session session=sessionfactory.getCurrentSession();
			session.save(blogComment);//insert into blogComment
			}
		
		public List<BlogComment> getAllBlogComments(int blogPostId) {
			Session session=sessionfactory.getCurrentSession();
			Query query=session.createQuery("from BlogComment where blogPost.id=?");
			query.setInteger(0, blogPostId);
			return query.list();
			
		}

}
