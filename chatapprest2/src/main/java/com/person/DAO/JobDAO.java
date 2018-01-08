package com.person.DAO;

import java.util.List;

import com.person.entity.Job;



public interface JobDAO {
	void saveJob(Job job);

	List<Job> getAllJobs();

	Job getJobById(int id);
}
