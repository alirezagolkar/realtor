package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.*;
import com.artinrayan.foodi.model.Attachment;
import com.artinrayan.foodi.web.util.UserUtil;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.User;
import com.artinrayan.foodi.web.util.ViewUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/host")
public class HostController {

    static final Logger logger = LoggerFactory.getLogger(HostController.class);

    @Autowired
    UserService userService;

    @Autowired
    HostService hostService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    AttachmentService attachmentService;

    /**
     * Loads all hosts
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/", "/hosts"}, method = RequestMethod.GET)
    public String getAllHosts(ModelMap modelMap) {
        List<Host> hosts = null;
        try {
            hosts = hostService.findAllHosts();
            ObjectMapper mapper = new ObjectMapper();
            modelMap.addAttribute("hostsStr", mapper.writeValueAsString(hosts));
            modelMap.addAttribute("hosts", hosts);
        } catch (JsonProcessingException e) {
            logger.error("Error to get hosts list due to: " + e.getMessage());
        }

        return ViewUtil.Views.HOME.getViewName();
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = {"/newHost"}, method = RequestMethod.GET)
    public String newHost(ModelMap model) {
        Host host = new Host();
        model.addAttribute("host", host);
        model.addAttribute("edit", false);
        return ViewUtil.Views.HOSTREGISTRATION.getViewName();
    }

    /**
     * @param host
     * @param result
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = {"/newHost"}, method = RequestMethod.POST)
    public String saveHost(@Valid Host host, BindingResult result,
                           ModelMap model) throws BusinessException {

        if (result.hasErrors()) {
            return ViewUtil.Views.HOSTREGISTRATION.getViewName();
        }

        User user = userService.findByUserId(UserUtil.getCurrentUser().getUserId());
        host.setUser(user);
        host.setCreationDate(new Date());
        host.setEnabled(true);
        hostService.saveHost(host);

        model.addAttribute("success", "Host " + host.getHostName() + " registered successfully");
        return ViewUtil.Views.HOSTSUCCESS.getViewName();
    }

    /**
     * @param hostId
     * @param model
     * @return
     */
    @RequestMapping(value = {"/edit-host-{hostId}"}, method = RequestMethod.GET)
    public String editHost(@PathVariable int hostId, ModelMap model) {
        Host host = null;
        User user = userService.findUserByUsername(UserUtil.getPrincipal());
        host = hostService.findHostByHostIdAndUserId(hostId, user);
        model.addAttribute("host", host);
        model.addAttribute("edit", true);
        return ViewUtil.Views.HOSTREGISTRATION.getViewName();
    }

    /**
     * @param host
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/edit-host-{hostId}")
    public String updateHost(@Valid Host host, BindingResult result,
                             ModelMap model) {

        if (result.hasErrors()) {
            return ViewUtil.Views.USERREGISTRATION.getViewName();
        }

        if (host.getCreationDate() == null)
            host.setCreationDate(new Date());
        hostService.updateHost(host);

        model.addAttribute("success", "Rental unit " + host.getHostName() + " edited successfully");
        return ViewUtil.Views.HOSTSUCCESS.getViewName();
    }

    /**
     * Loads hosts for current user
     *
     * @param modelMap
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = {"/hostList"}, method = RequestMethod.GET)
    public String getHosts(ModelMap modelMap) throws BusinessException {
        List<Host> hosts = hostService.findHostByUserId(UserUtil.getUserId());
        modelMap.addAttribute("hosts", hosts);
        return ViewUtil.Views.HOSTLIST.getViewName();
    }

    /**
     * Deletes a host for given host Id
     *
     * @param hostId
     * @return
     */
    @RequestMapping(value = {"/delete-host-{hostId}"}, method = RequestMethod.GET)
    public String removeHost(@PathVariable int hostId) {
        hostService.deleteHost(hostId);

        return "redirect:/host/" + ViewUtil.Views.HOSTLIST.getViewName();
    }

    /**
     * Loads host detail for a given host Id
     *
     * @param hostId
     * @param model
     * @return
     */
    @RequestMapping(value = {"/hostDetail-{hostId}"}, method = RequestMethod.GET)
    public String getHostDetail(@PathVariable int hostId, ModelMap model) {
        User user = null;
        try {
            user = userService.findByUserId(UserUtil.getCurrentUser().getUserId());
            Host host = hostService.findHostByHostIdAndUserId(hostId, user);
            List<Attachment> attachments = attachmentService.findAttachmentsByHostId(hostId);
            model.addAttribute("host", host);
            model.addAttribute("attachments", attachments);
            ObjectMapper mapper = new ObjectMapper();
            model.addAttribute("hostStr", mapper.writeValueAsString(host));
            model.addAttribute("edit", false);
        } catch (JsonProcessingException e) {
            logger.error("Error to get a host detail due to: " + e.getMessage());
        }

        return ViewUtil.Views.HOST.getViewName();
    }

    /**
     * Global exception handler
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ModelAndView handleCustomException(BusinessException ex) {

        ModelAndView model = new ModelAndView("error");
        model.addObject("errCode", ex.getCause());
        model.addObject("errMsg", ex.getMessage());

        return model;
    }

    /**
     * @return
     */
    @ModelAttribute("activationList")
    public Map<Boolean, String> getActivationList() {
        Map<Boolean, String> activationStatus = new LinkedHashMap<Boolean, String>();
        activationStatus.put(true, "Active");
        activationStatus.put(false, "Inactive");

        return activationStatus;
    }


}