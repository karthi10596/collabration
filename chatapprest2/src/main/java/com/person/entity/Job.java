package com.person.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table
public class Job {
	@Id
	private int jobid;
	private String jobtitle;
	@Lob//large character object
	private String jobDescription;
	private String skillsrequired;
	private int yrsOfExp;
	private int salary;
	private String companyname;
	private String location;
	private Date postedOn;
	
	
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getSkillsrequired() {
		return skillsrequired;
	}
	public void setSkillsrequired(String skillsrequired) {
		this.skillsrequired = skillsrequired;
	}
	
	public int getYrsOfExp() {
		return yrsOfExp;
	}
	public void setYrsOfExp(int yrsOfExp) {
		this.yrsOfExp = yrsOfExp;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}	
	
}
