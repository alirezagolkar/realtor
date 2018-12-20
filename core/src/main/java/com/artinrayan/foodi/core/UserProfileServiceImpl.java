package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User Profile Service Implementation
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	UserProfileDao dao;

	/**
	 * Loads user profile by a given Id
	 *
	 * @param id
	 * @return UserProfile object
	 */
	public UserProfile findById(int id) {
		return dao.findById(id);
	}

	/**
	 * Loads user profile by a type
	 *
	 * @param type
	 * @return UserProfile object
	 */
	public UserProfile findByType(String type){
		return dao.findByType(type);
	}

	public List<UserProfile> findAll() {
		return dao.findAll();
	}
}
