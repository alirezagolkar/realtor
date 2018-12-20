package com.artinrayan.foodi.web.webservice;

import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.model.Host;
import exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest service Api
 */
@RestController
@RequestMapping("/realtorRestService")
public class HostRestApi {

    static final Logger logger = LoggerFactory.getLogger(HostRestApi.class);

    @Autowired
    HostService hostService;


    /**
     * Retrieve All Hosts
     *
     * @return
     */
    @RequestMapping(value = RestURIConstants.GET_ALL_HOST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Host>> getHosts() {
        logger.info("Fetching Hosts");
        List<Host> hostList = hostService.findAllHosts();
        if (hostList.size() == 0) {
            return new ResponseEntity<List<Host>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Host>>(hostList, HttpStatus.OK);
    }

    /**
     * Retrieve Single Host
     *
     * @param id
     * @return
     */
    @RequestMapping(value = RestURIConstants.GET_HOST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Host> getUser(@PathVariable("id") int id) {
        logger.info("Fetching User with id " + id);
        Host host = hostService.findHostByHostId(id);
        if (host != null)
            return new ResponseEntity<Host>(host, HttpStatus.OK);
        else
            return new ResponseEntity<Host>(HttpStatus.NOT_FOUND);
    }

    /**
     * Create a Host
     *
     * @param host
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = RestURIConstants.CREATE_HOST, method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Host host, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Host " + host.getHostName());

        hostService.saveHost(host);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(host.getHostId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

    }

    /**
     * Update a User
     *
     * @param id
     * @param host
     * @return
     */
    @RequestMapping(value = RestURIConstants.UPDATE_HOST, method = RequestMethod.PUT)
    public ResponseEntity<Host> updateUser(@PathVariable("id") long id, @RequestBody Host host) {
        logger.info("Updating User " + id);

        hostService.saveHost(host);
        return new ResponseEntity<Host>(host, HttpStatus.OK);
    }

    /**
     * Delete a User
     *
     * @param id
     * @return
     */
    @RequestMapping(value = RestURIConstants.DELETE_HOST, method = RequestMethod.DELETE)
    public ResponseEntity<Host> deleteUser(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting User with id " + id);

        Host host = null;
        host = hostService.findHostByHostId(id);
        if (host != null) {
            hostService.deleteHost(id);
            return new ResponseEntity<Host>(HttpStatus.OK);
        }

        return new ResponseEntity<Host>(HttpStatus.NOT_FOUND);
    }
}
