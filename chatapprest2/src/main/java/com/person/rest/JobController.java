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

import com.person.DAO.JobDAO;
import com.person.DAO.UserDAO;
import com.person.entity.Job;
import com.person.entity.User;
import com.person.entity.error;


@RestController
public class JobController {
	@Autowired
    JobDAO jobdao;
	@Autowired
	 UserDAO userdao;
	@RequestMapping(value="/savejob",method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
		if(session.getAttribute("username")==null){
			error err=new error(5,"UnAuthroized User");
			return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		User user=userdao.getUserByUsername(username);
        if(user.getRole().equals("admin")){
        	try{
        		job.setPostedOn(new Date());
        	jobdao.saveJob(job);
        	return new ResponseEntity<Job>(job,HttpStatus.OK);
        	}catch(Exception e){
        		error err=new error(7,"Unable to insert job details " + e.getMessage());
        		return new ResponseEntity<error>(err,HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        }
        else{
        	error err=new error(6,"Access Denied");
        	return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
        }
		
	}
	@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		if(session.getAttribute("username")==null){
			error err=new error(5,"UnAuthroized User");
			return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs=jobdao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	@RequestMapping(value="/getjobbyid/{jobid}",method=RequestMethod.GET)
	public ResponseEntity<?> getJobById(@PathVariable int jobid,HttpSession session){
		
		if(session.getAttribute("username")==null){
			error err=new error(5,"UnAuthroized User");
			return new ResponseEntity<error>(err,HttpStatus.UNAUTHORIZED);
		}
		Job job=jobdao.getJobById(jobid);
		return new ResponseEntity<Job>(job,HttpStatus.OK);		
	}	

}
