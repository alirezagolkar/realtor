package com.artinrayan.foodi.core;


import com.artinrayan.foodi.model.User;
import exceptions.UserDaoException;

import java.util.List;

/**
 * Users data access interface
 */
public interface UserDao {

    /**
     * Loads a user with a given user Id
     *
     * @param id
     * @return user object
     */
    User findById(int id) throws UserDaoException;

    /**
     * Loads a user authentication info with a given username
     *
     * @param username
     * @return user object
     * @throws UserDaoException
     */
    User findUserAuthenticateInfoByUsername(String username) throws UserDaoException;

    /**
     * Loads a user based on natural Id
     *
     * @param username
     * @return user object
     * @throws UserDaoException
     */
    User loadUserByUsername(String username) throws UserDaoException;

    /**
     * Loads a user with a given username
     *
     * @param username
     * @return user object
     * @throws UserDaoException
     */
    User findUserByUsername(String username) throws UserDaoException;

    /**
     * Stores a user
     *
     * @param user
     * @throws UserDaoException
     */
    void save(User user) throws UserDaoException;

    /**
     * Deletes a user with given username
     *
     * @param username
     */
    void deleteByUsername(String username) throws UserDaoException;

    /**
     * Loads all users
     *
     * @return list of users
     * @throws UserDaoException
     */
    List<User> findAllUsers() throws UserDaoException;

}

