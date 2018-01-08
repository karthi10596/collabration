package com.person.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.person.DAO.UserDAO;
import com.person.entity.User;
import com.person.entity.error;

@RestController
public class MyController {
	@Autowired
	UserDAO userdao;
	@RequestMapping(value="/getmydetails/{fromId}",method=RequestMethod.GET)
	public ResponseEntity<?> getUserDetails(@PathVariable String fromId,HttpSession session){
		if(session.getAttribute("username")==null){
			error err=new error(5,"UnAuthroized User");
			return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
		}
		User user=userdao.getUserByUsername(fromId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

}
