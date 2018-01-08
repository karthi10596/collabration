package com.person.DAO;

import java.util.List;

import com.person.entity.Forum;



public interface ForumDAO {
	public void addForum(Forum forum);
	public List<Forum> getList();
	public void deleteforum(Forum forum);
}
