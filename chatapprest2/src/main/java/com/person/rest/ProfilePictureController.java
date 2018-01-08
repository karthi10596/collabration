package com.person.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.person.DAO.ProfilePictureDAO;
import com.person.entity.ProfilePicture;
import com.person.entity.error;

@RestController
public class ProfilePictureController {
	@Autowired
	ProfilePictureDAO profilePictureDao;
		@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePic(@RequestParam CommonsMultipartFile image,HttpSession session){
			System.out.println(session.getAttribute("username"));
		
		if(session.getAttribute("username")==null){
			error err=new error(5,"UnAuthroized User");
			return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setUsername(username);
		profilePicture.setImage(image.getBytes());
		try{
		profilePictureDao.save(profilePicture);
		return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);
		}catch(Exception e){
			error err=new error(6,"Unable to upload");
			return new ResponseEntity<error>(err,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
		
		
		@RequestMapping(value="/getimage/{username}", method=RequestMethod.GET)
		public @ResponseBody byte[] getImage(@PathVariable String username,HttpSession session)
		{
		
			if(session.getAttribute("username")==null){
				return null;
		}
		else
		{
			ProfilePicture profilePicture=profilePictureDao.getProfilePicture(username);
			if(profilePicture==null)
				return null;
			
			System.out.println(profilePicture.getImage());
			return profilePicture.getImage();
	}
	}

}
