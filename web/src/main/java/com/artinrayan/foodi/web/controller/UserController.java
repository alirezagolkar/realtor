package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.UserProfileService;
import com.artinrayan.foodi.core.UserService;
import com.artinrayan.foodi.model.User;
import com.artinrayan.foodi.model.UserProfile;
import com.artinrayan.foodi.web.util.ViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller to handle requests to users
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("roles")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;


    /**
     * @param model
     * @return
     */
    @RequestMapping(value = {"/userList"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = null;
        users = userService.findAllUsers();
        model.addAttribute("users", users);
        return ViewUtil.Views.USERLIST.getViewName();
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = {"/angularMap"}, method = RequestMethod.GET)
    public String angularMap(ModelMap model) {

        return "angularMap";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = {"/newUser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return ViewUtil.Views.USERREGISTRATION.getViewName();
    }

    /**
     * @param user
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = {"/newUser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "userRegistration";
        }

        if (!userService.isUserUnique(user.getId(), user.getUsername())) {
            ResourceBundle rb = ResourceBundle.
                    getBundle("message.user.Messages", LocaleContextHolder.getLocale());

            FieldError ssoError = new FieldError("user", "username", messageSource.
                    getMessage(rb.getString("non.unique.username"),
                            new String[]{user.getUsername()}, LocaleContextHolder.getLocale()));
            result.addError(ssoError);
            return "userRegistration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
        return "userRegistrationSuccess";
    }

    /**
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = {"/edit-user-{username}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String username, ModelMap model) {
        User user = userService.findUserAuthenticateInfoByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return ViewUtil.Views.USERREGISTRATION.getViewName();
    }

    /**
     * @param user
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = {"/edit-user-{username}"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, Locale locale) {

        if (result.hasErrors()) {
            return ViewUtil.Views.USERREGISTRATION.getViewName();
        }

        if (userService.isUserUnique(user.getId(), user.getUsername())) {
            FieldError ssoError = new FieldError("user", "ssoId",
                messageSource.getMessage("non.unique.username", null, locale));
            result.addError(ssoError);
            return ViewUtil.Views.USERREGISTRATION.getViewName();
        }

        userService.updateUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        return ViewUtil.Views.USEREGISTRATIONSUCCESS.getViewName();
    }

    /**
     * @param username
     * @return
     */
    @RequestMapping(value = {"/delete-user-{username}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/user/userList";
    }

    @RequestMapping(value = {"/redirect-user"}, method = RequestMethod.POST)
    @ResponseBody
    public String redirect() {
        return "/host/newHost";
    }

    /**
     * @return
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

}
