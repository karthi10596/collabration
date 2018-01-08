package com.person.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.person.entity.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionfactory;
	
	public void adduser(User user) {
		// TODO Auto-generated method stub
		
		try
		{
			Session session=sessionfactory.openSession();
			//Transaction trans=session.beginTransaction();
			session.save(user);
			//trans.commit();
			session.flush();
			session.close();
		}
		
		catch(Exception ex)
		{
			System.out.println("Error="+ex);
		}
		
		
	}
	public List<User> getList()
	{
		Session session=sessionfactory.openSession();
		//Transaction trans=session.beginTransaction();
		List<User> showuser=session.createQuery("from User").list();
		//trans.commit();
		session.close();
		return showuser;
		
	}
	public void deleteuser(User user)
	{
		Session session=sessionfactory.openSession();
		//Transaction trans=session.beginTransaction();  
		session.delete(user);
		//trans.commit();
		session.close();

	}
	public User getUser(String username)
	{
			Session session=sessionfactory.openSession();
			//Transaction trans=session.beginTransaction();
			User user=(User)session.get(User.class,username);
			//trans.commit();
			session.flush();
			session.close();
			return user;
		}
		
	public void updateUser(User user)
	{
		Session session=sessionfactory.openSession();
		//Transaction trans=session.beginTransaction(); 
		session.update(user);
		//trans.commit();
		session.flush();
		session.close();
		}
	public User login(User user) {
		Session session=sessionfactory.openSession();
		//Transaction trans=session.beginTransaction();
		Query query=session.createQuery("from User where username=? and password=?");
		query.setString(0, user.getUsername());
		query.setString(1, user.getPassword());
		return (User)query.uniqueResult();
		
	}
	public User getUserByUsername(String username)
	{
		Session session=sessionfactory.openSession();
		//Transaction trans=session.beginTransaction();
		User user=(User)session.get(User.class,username);
		return user;
	}
	public User validateUsername(String username) {
		Session session=sessionfactory.getCurrentSession();
		//Transaction trans=session.beginTransaction();
		User user=(User)session.get(User.class, username);
		return user;
	}
	public User validateEmailId(String emailId) {
		Session session=sessionfactory.getCurrentSession();
		//Transaction trans=session.beginTransaction();
		User user=(User)session.get(User.class, emailId);
		return user;
	}


}
