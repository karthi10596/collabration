package com.person.rest;

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

import com.person.DAO.FriendDAO;
import com.person.DAO.UserDAO;
import com.person.entity.Friend;
import com.person.entity.User;
import com.person.entity.error;

@RestController
public class FriendController {
	@Autowired
	private FriendDAO frienddao;
@Autowired
private UserDAO userdao;
	@RequestMapping(value="/getsuggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> getListOfSuggestedUsers(HttpSession session){
		if(session.getAttribute("username")==null){
			error err=new error(5,"UnAuthroized User");
			return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		List<User> suggestUsers=frienddao.getListOfSuggestedUsers(username);
				return new ResponseEntity<List<User>>(suggestUsers,HttpStatus.OK);
	}
	
	@RequestMapping(value="/friendRequest/{toId}",method=RequestMethod.POST)
	public ResponseEntity<?> friendRequest(@PathVariable String toId,HttpSession session){
	if(session.getAttribute("username")==null){
		error err=new error(5,"UnAuthroized User");
		return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
	}
	String username=(String)session.getAttribute("username");
	frienddao.addFriendRequest(username, toId);
	return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@RequestMapping(value="/getpendingrequests",method=RequestMethod.GET)
	public ResponseEntity<?> getPendingRequests(HttpSession session){
		if(session.getAttribute("username")==null){
			error err=new error(5,"UnAuthroized User");
			return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		List<Friend> pendingRequests=frienddao.getPendingRequests(username);
		return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getuserdetails/{fromId}",method=RequestMethod.GET)
	public ResponseEntity<?> getUserDetails(@PathVariable String fromId,HttpSession session){
		if(session.getAttribute("username")==null){
			error err=new error(5,"UnAuthroized User");
			return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
		}
		User user=userdao.getUserByUsername(fromId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updatependingrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> updatePendingRequest(@RequestBody Friend pendingRequest,HttpSession session){
		if(session.getAttribute("username")==null){
			error err=new error(5,"UnAuthroized User");
			return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
		}
		frienddao.updatePendingRequest(pendingRequest);//update status A/D
		return new ResponseEntity<Friend>(pendingRequest,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/friendslist",method=RequestMethod.GET)
	public ResponseEntity<?> listOfFriends(HttpSession session){
		if(session.getAttribute("username")==null){
		error err=new error(5,"UnAuthroized User");
			return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
		}
			
		String username=(String)session.getAttribute("username");
			List<Friend> friends=frienddao.listOfFriends(username);
			return new ResponseEntity<List<Friend>>(friends,HttpStatus.OK);
	}
	

}
