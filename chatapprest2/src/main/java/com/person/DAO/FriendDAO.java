package com.person.DAO;

import java.util.List;

import com.person.entity.Friend;
import com.person.entity.User;

public interface FriendDAO {

List<User> getListOfSuggestedUsers(String username);
void addFriendRequest(String username,String toId);
List<Friend> getPendingRequests(String username);
void updatePendingRequest(Friend pendingRequest);
List<Friend> listOfFriends(String username);
}
