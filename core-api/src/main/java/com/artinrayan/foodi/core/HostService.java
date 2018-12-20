package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.User;
import exception.BusinessException;

import java.util.List;

/**
 * Host Service
 */
public interface HostService {

    /**
     * Loads a host by a given user Id
     *
     * @param userId
     * @return list of hosts
     */
    List<Host> findHostByUserId(int userId);

    /**
     * Loads a host by a given host Id
     *
     * @param hostId
     * @return host object
     */
    Host findHostByHostId(int hostId);

    /**
     * Loads all hosts
     *
     * @return list of hosts
     */
    List<Host> findAllHosts();

    /**
     * Loads a host by given host and user Ids
     *
     * @param hostId
     * @param user
     * @return host object
     */
    Host findHostByHostIdAndUserId(int hostId, User user);

    /**
     * Saves a host
     *
     * @param host
     */
    void saveHost(Host host);

    /**
     * Updates host
     *
     * @param host
     */
    void updateHost(Host host);

    /**
     * Deletes a host by a given host Id
     *
     * @param hostId
     */
    void deleteHost(int hostId);
}
