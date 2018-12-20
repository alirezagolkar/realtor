package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.User;
import exception.BusinessException;
import exceptions.UserDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User Service Implementation
 */
@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;

	/**
	 * Loads a user with a given user Id
	 *
	 * @param id
	 * @return user object
	 */
	public User findByUserId(int id) {
		return dao.findById(id);
	}

	/**
	 * Loads a user authentication info with a given username
	 *
	 * @param username
	 * @return user object
	 */
	public User findUserAuthenticateInfoByUsername(String username) {
		User user = dao.findUserAuthenticateInfoByUsername(username);
		return user;
	}

	/**
	 * Loads a user based on username
	 *
	 * @param username
	 * @return user object
	 */
	public User loadUserByUsername(String username)
	{
		return dao.loadUserByUsername(username);
	}

	/**
	 * Loads a user with a given username
	 *
	 * @param username
	 * @return user object
	 */
	public User findUserByUsername(String username) {
		User user = dao.findUserByUsername(username);
		user.getUserHosts();
		return user;
	}

	/**
	 * Stores a user
	 *
	 * @param user
	 */
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	/**
	 * Update a given user
	 *
	 * @param user
     */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setUsername(user.getUsername());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	/**
	 * Deletes a user by given username
	 *
	 * @param username
     */
	public void deleteUserByUsername(String username) {
		dao.deleteByUsername(username);
	}

	/**
	 * Loads all users
	 *
	 * @return list of users
	 */
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	/**
	 * Determines if a user is uniques
	 *
	 * @param id
	 * @param username
     * @return
     */
	public boolean isUserUnique(Integer id, String username) {
		User user = findUserAuthenticateInfoByUsername(username);
		return ( user == null || ((id != null) && (user.getId() != id)));
	}
	
}
