package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.User;
import exceptions.HostDaoException;

import java.util.List;

/**
 * Hosts data access interface
 */
public interface HostDao {

    /**
     * Loads a host by a given user Id
     *
     * @param userId
     * @return list of hosts
     * @throws HostDaoException
     */
    List<Host> findByUserId(int userId) throws HostDaoException;

    /**
     * Loads a host by a given host Id
     *
     * @param hostId
     * @return host object
     * @throws HostDaoException
     */
    Host findByHostId(int hostId) throws HostDaoException;

    /**
     * Loads all hosts
     *
     * @return list of hosts
     */
    List<Host> findAllHosts() throws HostDaoException;

    /**
     * Loads a host by given host and user Ids
     *
     * @param hostId
     * @param user
     * @return host object
     */
    Host findHostByHostIdAndUserId(int hostId, User user) throws HostDaoException;

    /**
     * Saves a host
     *
     * @param host
     */
    void save(Host host) throws HostDaoException;

    /**
     * Updates a host
     *
     * @param host
     */
    void update(Host host) throws HostDaoException;

    /**
     * Deletes a host by a given host Id
     *
     * @param hostId
     */
    void delete(int hostId) throws HostDaoException;
}
