package com.person.rest;




import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.person.DAO.UserDAO;

import com.person.entity.User;
import com.person.entity.error;

@RestController
public class UserController {
	@Autowired
    UserDAO userdao;
	
	@RequestMapping(value="/getAllUsers",method=RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session){
		if(session.getAttribute("username")==null){
    		error err=new error(5,"UnAuthorized User");
    		return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
    	}

		String username=(String)session.getAttribute("username");
    	User user=userdao.getUserByUsername(username);
    	return new ResponseEntity<User>(user,HttpStatus.OK);

	}
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user){
		try{
	    	User duplicateUser	=userdao.validateUsername(user.getUsername());
	    	if(duplicateUser!=null){
	    		//response.data is error
	    		//response.status is 406 NOT_ACCEPTABLE
	    		error err=new error(2,"Username already exists.. please enter different username");
	    		return new ResponseEntity<error>(err,HttpStatus.NOT_ACCEPTABLE);
	    	}
	    	
			userdao.adduser(user);
			//response.data is user
			//response.status is 200 OK
			return new ResponseEntity<User>(user,HttpStatus.OK);
	    	}catch(Exception e){
	    		//response.data is error
	    		//response.status is 406 NOT_ACCEPTABLE
	    		error err=new error(1,"Unable to register user details " + e.getMessage());
	    		return new ResponseEntity<error>(err,HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
		}
	    
	@RequestMapping(value="/login",method=RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
    	User validuser=userdao.login(user);
    	if(validuser==null){//invalid credentials
    		error err=new error(4,"Invalid username/password.. please enter valid username/pwd");
    		return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
    	}

    	validuser.setOnline(true);
    	userdao.updateUser(validuser);//update only the online status from 0 to 1
    	session.setAttribute("username", validuser.getUsername());
    	return new ResponseEntity<User>(validuser,HttpStatus.OK);
    }
	@RequestMapping(value="/logout",method=RequestMethod.GET)
    public ResponseEntity<?> logout(HttpSession session){
    	
    	String username=(String)session.getAttribute("username");
    	User user=userdao.getUserByUsername(username);
    	user.setOnline(false);
    	userdao.updateUser(user);
    	session.removeAttribute("username");
    	session.invalidate();
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
	@RequestMapping(value="/deleteuser/{username}",method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("userId")String username,@RequestBody User user) {
        user.setUsername(username);
        userdao.deleteuser(user);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
	@RequestMapping(value="/upadteuser/{username}",method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("userId") String username, @RequestBody User user) {
        System.out.println("Updating User " + username);
         
        User currentUser = userdao.getUser(username);
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setEmailId(user.getEmailId());
         
        userdao.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
}
