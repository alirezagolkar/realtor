package com.artinrayan.foodi.core;


import com.artinrayan.foodi.model.UserProfile;

import java.util.List;

/**
 * User profiles data access interface
 */
public interface UserProfileDao {

    /**
     * Loads all user profiles
     *
     * @return list of user profiles
     */
    List<UserProfile> findAll();

    /**
     * Loads user profile by a type
     *
     * @param type
     * @return UserProfile object
     */
    UserProfile findByType(String type);

    /**
     * Loads user profile by a given Id
     *
     * @param id
     * @return UserProfile object
     */
    UserProfile findById(int id);
}
