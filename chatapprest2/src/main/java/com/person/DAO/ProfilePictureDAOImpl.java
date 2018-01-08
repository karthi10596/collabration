package com.person.DAO;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.person.entity.ProfilePicture;
@Repository
@Transactional
public class ProfilePictureDAOImpl implements ProfilePictureDAO {
	@Autowired
	private SessionFactory sessionfactory;
	
	public void save(ProfilePicture profilePicture){
		Session session=sessionfactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);
	}
	
	public ProfilePicture getProfilePicture(String username){
		Session session=sessionfactory.getCurrentSession();
		ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class,username);
		return profilePicture;
	}
	
	

}
