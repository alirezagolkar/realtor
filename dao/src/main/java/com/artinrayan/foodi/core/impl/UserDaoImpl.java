package com.artinrayan.foodi.core.impl;

import com.artinrayan.foodi.core.AbstractDao;
import com.artinrayan.foodi.core.UserDao;
import com.artinrayan.foodi.model.User;
import exceptions.UserDaoException;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implements user dao interface and handle database operations for users
 */
@Repository("userDao")
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	/**
	 * Loads a user with a given user Id
	 *
	 * @param id
	 * @return user object
     */
	public User findById(int id) {
		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	/**
	 * Loads a user authentication info with a given username
	 *
	 * @param username
	 * @return user object
	 * @throws UserDaoException
     */
	public User findUserAuthenticateInfoByUsername(String username) throws UserDaoException {
		logger.info("username : {}", username);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	/**
	 * Loads a user based on natural Id
	 *
	 * @param username
	 * @return user object
	 * @throws UserDaoException
     */
	public User loadUserByUsername (String username) throws UserDaoException
	{
		return (User) getSession().bySimpleNaturalId(User.class).load(username);
	}

	/**
	 * Loads a user with a given username
	 *
	 * @param username
	 * @return user object
	 * @throws UserDaoException
     */
	public User findUserByUsername(String username) throws UserDaoException {
		logger.info("username : {}", username);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
			Hibernate.initialize(user.getUserHosts());
		}
		return user;
	}

	/**
	 * Loads all users
	 *
	 * @return list of users
	 * @throws UserDaoException
     */
	public List<User> findAllUsers() throws UserDaoException {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return users;
	}

	/**
	 * Stores a user
	 *
	 * @param user
	 * @throws UserDaoException
     */
	public void save(User user)throws UserDaoException {
		persist(user);
	}

	/**
	 * Deletes a user with given username
	 *
	 * @param username
     */
	public void deleteByUsername(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		User user = (User)crit.uniqueResult();
		delete(user);
	}

}
