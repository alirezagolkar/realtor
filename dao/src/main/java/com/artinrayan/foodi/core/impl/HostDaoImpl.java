package com.artinrayan.foodi.core.impl;

import com.artinrayan.foodi.core.AbstractDao;
import com.artinrayan.foodi.core.HostDao;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.User;
import exceptions.HostDaoException;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implements host dao interface and handle database operations for hosts
 */
@Repository("hostDao")
@Transactional(propagation = Propagation.MANDATORY)
public class HostDaoImpl extends AbstractDao<Integer, Host> implements HostDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    /**
     * Loads a host by a given user Id
     *
     * @param userID
     * @return list of hosts
     * @throws HostDaoException
     */
    @Override
    public List<Host> findByUserId(int userID) throws HostDaoException {
        Query query = getSession().getNamedQuery(Host.GET_HOST_BY_USER_ID);
        query.setInteger("userId", userID);
        return query.list();
    }

    /**
     * Loads a host by a given host Id
     *
     * @param hostId
     * @return host object
     * @throws HostDaoException
     */
    @Override
    public Host findByHostId(int hostId) throws HostDaoException {
        Query query = getSession().getNamedQuery(Host.GET_HOST_BY_HOST_ID);
        query.setInteger("hostId", hostId);
        Host host = (Host) query.uniqueResult();

        if (host != null) {
            Hibernate.initialize(host.getAttachments());
        }

        return host;
    }

    /**
     * Loads all hosts
     *
     * @return list of hosts
     */
    @Override
    public List<Host> findAllHosts() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("hostName"));
        criteria.add(Restrictions.eq("enabled", true));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Host> hostList = criteria.list();

        return hostList;
    }

    /**
     * Loads a host by given host and user Ids
     *
     * @param hostId
     * @param user
     * @return host object
     */
    @Override
    public Host findHostByHostIdAndUserId(int hostId, User user) {
        logger.info("hostId : {}", hostId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("hostId", hostId));
        crit.add(Restrictions.eq("user", user));
        Host Host = (Host) crit.uniqueResult();
        return Host;
    }

    /**
     * Saves a host
     *
     * @param host
     */
    @Override
    public void save(Host host) {
        persist(host);
    }

    /**
     * Updates a host
     *
     * @param host
     */
    @Override
    public void update(Host host) {
        persist(host);
    }

    /**
     * Deletes a host by a given host Id
     *
     * @param hostId
     */
    @Override
    public void delete(int hostId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("hostId", hostId));
        Host host = (Host) criteria.uniqueResult();
        if (host != null) {
            delete(host);
        }
    }
}
