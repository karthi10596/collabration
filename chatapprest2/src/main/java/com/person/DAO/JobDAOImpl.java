package com.person.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.person.entity.Job;
@Repository
@Transactional
public class JobDAOImpl implements JobDAO {
@Autowired
SessionFactory sessionfactory;
public void saveJob(Job job) {
	Session session=sessionfactory.getCurrentSession();
	session.save(job);
}
public List<Job> getAllJobs() {
	Session session=sessionfactory.getCurrentSession();
	Query query=session.createQuery("from Job");
	return query.list();
}
public Job getJobById(int jobid) {
	Session session=sessionfactory.getCurrentSession();
	Job job=(Job)session.get(Job.class,jobid);
	return job;
}

}
