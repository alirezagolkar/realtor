package com.artinrayan.foodi.core.impl;

import com.artinrayan.foodi.core.AbstractDao;
import com.artinrayan.foodi.core.UserProfileDao;
import com.artinrayan.foodi.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation for userProfileDao to handle operation for user profiles
 */
@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao {

    /**
     * Loads user profile by a given Id
     *
     * @param id
     * @return UserProfile object
     */
    public UserProfile findById(int id) {
        return getByKey(id);
    }

    /**
     * Loads user profile by a type
     *
     * @param type
     * @return UserProfile object
     */
    public UserProfile findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (UserProfile) crit.uniqueResult();
    }

    /**
     * Loads all user profiles
     *
     * @return list of user profiles
     */
    @SuppressWarnings("unchecked")
    public List<UserProfile> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<UserProfile>) crit.list();
    }

}
