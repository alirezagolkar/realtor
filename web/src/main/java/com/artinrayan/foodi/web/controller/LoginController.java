package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.web.util.ViewUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller to handle login and logout
 */
@Controller
@RequestMapping("/")
public class LoginController {

    static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    HostService hostService;

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        return ViewUtil.Views.ACCESSDENIED.getViewName();
    }

    /**
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return ViewUtil.Views.LOGIN.getViewName();
        } else {
            return ViewUtil.Views.HOME.getViewName();
        }
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /**
     *
     * @return
     */
    @GetMapping("/error")
    public String getErrorPage() {
        return ViewUtil.Views.ERRORPAGE.getViewName();
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

}
