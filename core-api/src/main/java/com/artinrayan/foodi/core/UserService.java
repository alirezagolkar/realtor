package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.User;
import exception.BusinessException;

import java.util.List;

/**
 * Users Service
 */
public interface UserService {

	/**
	 * Loads a user with a given user Id
	 *
	 * @param id
	 * @return user object
	 */
	User findByUserId(int id);

	/**
	 * Loads a user authentication info with a given username
	 *
	 * @param username
	 * @return user object
	 */
	User findUserAuthenticateInfoByUsername(String username);

	/**
	 * Loads a user based onusername
	 *
	 * @param username
	 * @return user object
	 */
	public User loadUserByUsername(String username);

	/**
	 * Loads a user with a given username
	 *
	 * @param username
	 * @return user object
	 */
	User findUserByUsername(String username);

	/**
	 * Stores a user
	 *
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * Update a given user
	 *
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * Deletes a user by given username
	 *
	 * @param username
	 */
	void deleteUserByUsername(String username);

	/**
	 * Loads all users
	 *
	 * @return list of users
	 */
	List<User> findAllUsers();

	/**
	 * Determines if a user is uniques
	 *
	 * @param id
	 * @param username
	 * @return
	 */
	boolean isUserUnique(Integer id, String username);

}