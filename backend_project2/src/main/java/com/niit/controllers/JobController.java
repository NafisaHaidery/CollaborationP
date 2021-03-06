package com.niit.controllers;

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

import com.niit.dao.JobDao;
import com.niit.model.Job;
import com.niit.model.Users;
import com.niit.model.Error;

@RestController
public class JobController {
	@Autowired
private JobDao jobDao;
	@RequestMapping(value="/saveJob",method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
		Users users=(Users)session.getAttribute("user");
		if(users==null)//If user tries to insert job without login.
		{
			Error error=new Error(3,"Unauthorized User..");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try{
			if(users.getRole().equals("Admin")){
				job.setPostedOn(new Date());
				jobDao.saveJob(job);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else
			{
				Error error=new Error(4,"Access Denied..");//If the user is logged in,but the role is not admin.
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); 
			}
	}catch(Exception e){
			Error error=new Error(1,"Unable to insert job details.." + e.getMessage());//If the user is logged in as admin but there is exception while inserting the job data.
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		Users users=(Users)session.getAttribute("user");
		if(users==null)
		{
			Error error=new Error(3,"Unauthorized User..");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs=jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	@RequestMapping(value="/getjobbyid/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getJobById(@PathVariable int id,HttpSession session){
		Users users=(Users)session.getAttribute("user");
		if(users==null)
		{
			Error error=new Error(3,"Unauthorized User..");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Job job=jobDao.getJobById(id);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
}
}