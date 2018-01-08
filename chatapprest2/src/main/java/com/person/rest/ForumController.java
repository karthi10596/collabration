package com.person.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.person.DAO.ForumDAO;
import com.person.entity.Forum;

@RestController
public class ForumController {
	@Autowired
    ForumDAO forumdao;
	
	@RequestMapping(value="/getAllForums",method=RequestMethod.GET)
	public List<Forum> getAllForum(){
		System.out.println("================getAllUsers=====================");
		return forumdao.getList();
	}
	@RequestMapping(value="/registerforum",method=RequestMethod.POST)
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum){
		 forumdao.addForum(forum);
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
			}
	@RequestMapping(value="/deleteforum/{ForumId}",method=RequestMethod.DELETE)
	public ResponseEntity<Forum> deleteForum(@PathVariable("ForumId")int ForumId ,@RequestBody Forum forum) {
		forum.setForumId(ForumId);
        forumdao.deleteforum(forum);
        return new ResponseEntity<Forum>(HttpStatus.NO_CONTENT);
    }

}
