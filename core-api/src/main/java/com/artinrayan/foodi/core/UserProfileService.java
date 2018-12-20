package com.artinrayan.foodi.core;


import com.artinrayan.foodi.model.UserProfile;

import java.util.List;

/**
 * User profile Service
 */
public interface UserProfileService {

	/**
	 * Loads user profile by a given Id
	 *
	 * @param id
	 * @return UserProfile object
	 */
	UserProfile findById(int id);

	/**
	 * Loads user profile by a type
	 *
	 * @param type
	 * @return UserProfile object
	 */
	UserProfile findByType(String type);

	/**
	 * Loads all user profiles
	 *
	 * @return list of user profiles
	 */
	List<UserProfile> findAll();
	
}
