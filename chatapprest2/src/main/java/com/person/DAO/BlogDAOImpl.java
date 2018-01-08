package com.person.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.person.entity.Blog;
import com.person.entity.User;

@Repository
public class BlogDAOImpl implements BlogDAO {
	@Autowired
	SessionFactory sessionfactory;
	
	public void addBlog(Blog blog) {
		// TODO Auto-generated method stub
		
		try
		{
			Session session=sessionfactory.openSession();
			Transaction trans=session.beginTransaction();
			session.save(blog);
			trans.commit();
			session.flush();
			session.close();
		}
		
		catch(Exception ex)
		{
			System.out.println("Error="+ex);
		}
		
		
	}
	public List<Blog> getList()
	{
		Session session=sessionfactory.openSession();
		Transaction trans=session.beginTransaction();
		List<Blog> showuser=session.createQuery("from Blog").list();
		trans.commit();
		session.close();
		return showuser;
		
	}
	public void deleteblog(Blog blog)
	{
		Session session=sessionfactory.openSession();
		Transaction trans=session.beginTransaction();  
		session.delete(blog);
		trans.commit();
		session.close();

	}
	public Blog getBlog(int BlogID)
	{
			Session session=sessionfactory.openSession();
			Transaction trans=session.beginTransaction();
			Blog blog=(Blog)session.get(Blog.class,BlogID);
			trans.commit();
			session.flush();
			session.close();
			return blog;
		}
		
	public void updateBlog(Blog blog)
	{
		Session session=sessionfactory.openSession();
		Transaction trans=session.beginTransaction(); 
		session.update(blog);
		trans.commit();
		session.flush();
		session.close();
		}
	@Transactional
	public boolean approveblog(Blog blog)
	{
		try
		{
			blog.setStatus("A");
			sessionfactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("exception occured:"+e);
			return false;
		}
	}

}
