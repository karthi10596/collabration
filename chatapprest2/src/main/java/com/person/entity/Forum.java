package com.person.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class Forum {
@Id
private int ForumId;
private String Forumname,Forumcontent,status;
public int getForumId() {
	return ForumId;
}
public void setForumId(int forumId) {
	ForumId = forumId;
}
public String getForumname() {
	return Forumname;
}
public void setForumname(String forumname) {
	Forumname = forumname;
}
public String getForumcontent() {
	return Forumcontent;
}
public void setForumcontent(String forumcontent) {
	Forumcontent = forumcontent;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}
