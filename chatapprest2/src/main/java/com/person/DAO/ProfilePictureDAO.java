package com.person.DAO;

import com.person.entity.ProfilePicture;

public interface ProfilePictureDAO {

void save(ProfilePicture profilePicture);
ProfilePicture getProfilePicture(String username);
}
