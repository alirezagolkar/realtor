package com.artinrayan.foodi.web.api;

import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.core.UserService;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.User;
import com.artinrayan.foodi.web.util.UserUtil;
import exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;

/**
 * Api controller to handle ajax requests to hosts
 */
@RestController
public class HostApiController {

    static final Logger logger = LoggerFactory.getLogger(HostApiController.class);

    @Autowired
    HostService hostService;

    @Autowired
    UserService userService;

    /**
     * Retrieve All Hosts
     *
     * @return
     */
    @RequestMapping(value = "/hostdetails", method = RequestMethod.GET)
    public ResponseEntity<List<Host>> listAllHosts() {
        List<Host> hosts = null;
        hosts = hostService.findAllHosts();
        if(hosts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(hosts, HttpStatus.OK);
    }

    /**
     * Retrieves a Single Host
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/host/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Host> getHost(@PathVariable("id") int id) {
        System.out.println("Fetching Host with id " + id);
        Host host = null;
        host = hostService.findHostByHostId(id);
        if (host == null) {
            System.out.println("Host with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(host, HttpStatus.OK);
    }

    /**
     * Creates a Host
     *
     * @param host
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/host", method = RequestMethod.POST)
    public ResponseEntity<Void> createHost(@RequestBody Host host, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Host " + host.getHostName());

        User user = userService.findUserByUsername(UserUtil.getCurrentUser().getUsername());
        host.setUser(user);
        host.setEnabled(true);
        host.setCreationDate(new Date());
        hostService.saveHost(host);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/host/{id}").buildAndExpand(host.getHostId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    /**
     * Updates a Host
     *
     * @param id
     * @param host
     * @return
     */
    @RequestMapping(value = "/host/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateHost(@PathVariable("id") int id, @RequestBody Host host) {
        System.out.println("Updating Host " + id);

        Host currentHost = null;
        currentHost = hostService.findHostByHostId(id);
        if (currentHost == null) {
            System.out.println("Host with id " + id + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        currentHost.setHostName(host.getHostName());
        currentHost.setHostDetail(host.getHostDetail());

        hostService.updateHost(currentHost);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes a Host
     *
     * @param host
     * @return
     */
    @RequestMapping(value="/deletehost",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity DeleteUser(@RequestBody Host host) {

        Host currentHost = hostService.findHostByHostId(host.getHostId());
        if (currentHost == null) {
            System.out.println("Unable to delete. Host with id " + host.getHostId() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hostService.deleteHost(host.getHostId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
