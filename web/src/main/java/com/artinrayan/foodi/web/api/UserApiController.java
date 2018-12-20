package com.artinrayan.foodi.web.api;

import com.artinrayan.foodi.core.UserService;
import com.artinrayan.foodi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Api controller to handle ajax requests to users
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {
 
    @Autowired
    UserService userService;


    /**
     * Retrieve All Users
     *
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = null;
        users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    /**
     * Retrieve Single User
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        User user = null;
        user = userService.findByUserId(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    /**
     * Creates a User
     *
     * @param user
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getUsername());

        userService.saveUser(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Updates a User
     *
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        User currentUser = null;
        currentUser = userService.findByUserId(id);
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());

        userService.updateUser(currentUser);

        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    /**
     * Deletes a User
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);

        User user = null;
        user = userService.findByUserId(id);

        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUserByUsername(user.getFirstName());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}