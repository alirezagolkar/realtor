package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.User;
import exception.BusinessException;
import exceptions.HostDaoException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Host Service Implementation
 */
@Service("hostService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class HostServiceImpl implements HostService {

    @Autowired
    private HostDao hostDao;

    /**
     * Loads a host by a given user Id
     *
     * @param userId
     * @return list of hosts
     * @throws HostDaoException
     */
    @Override
    @AfterThrowing(pointcut = "businessService()", throwing = "ex")
    public List<Host> findHostByUserId(int userId) {
        return hostDao.findByUserId(userId);
    }

    /**
     * Loads a host by a given host Id
     *
     * @param hostId
     * @return host object
     * @throws HostDaoException
     */
    @Override
    public Host findHostByHostId(int hostId) {
        return hostDao.findByHostId(hostId);
    }

    /**
     * Loads all hosts
     *
     * @return list of hosts
     */
    @Override
    public List<Host> findAllHosts() {
        return hostDao.findAllHosts();
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
        return hostDao.findHostByHostIdAndUserId(hostId, user);
    }

    /**
     * Saves a host
     *
     * @param host
     */
    @Override
    public void saveHost(Host host) {
        hostDao.save(host);
    }

    /**
     * Updates host
     *
     * @param host
     */
    @Override
    public void updateHost(Host host) {

        Host entity = hostDao.findByHostId(host.getHostId());
        if (entity != null) {
            entity.setHostName(host.getHostName());
            entity.setCreationDate(host.getCreationDate());
            entity.setEnabled(host.getEnabled());
            entity.setHostDetail(host.getHostDetail());
            entity.setHostAddress(host.getHostAddress());
            entity.setHostCity(host.getHostCity());
            entity.setHostCountry(host.getHostCountry());
            entity.setHostPhoneNumber(host.getHostPhoneNumber());
            entity.setHostWebSite(host.getHostWebSite());
            entity.setLatitude(host.getLatitude());
            entity.setLongitude(host.getLongitude());
            entity.setAttachments(host.getAttachments());
        }

    }

    /**
     * Deletes a host by a given host Id
     *
     * @param hostId
     */
    @Override
    public void deleteHost(int hostId) {
        hostDao.delete(hostId);
    }
}
