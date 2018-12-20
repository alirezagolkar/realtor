package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.AttachmentService;
import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.model.Attachment;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.web.util.ViewUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

/**
 * Controller to handle requests to attachments
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController {

    static final Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    HostService hostService;

    /**
     * @param id
     * @param response
     */
    @RequestMapping(value = "/getAttachmentById", method = RequestMethod.GET)
    public void getAttachmentById(@RequestParam("id") Integer id, HttpServletResponse response){

        try {
            Attachment attachment = attachmentService.findAttachmentById(id);
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(attachment.getFileContent());
            response.getOutputStream().close();
        } catch (IOException e) {
            logger.error("Error to get attachment due to: " + e.getMessage());
        }
    }

    /**
     * @param hostId
     * @param model
     * @return
     */
    @GetMapping("/manage-attachment-{hostId}")
    public String getHostAttachments(@PathVariable int hostId, ModelMap model) {
        Host host = null;
        host = hostService.findHostByHostId(hostId);
        Attachment attachment = new Attachment();
        attachment.setHost(host);
        model.addAttribute("attachment", attachment);

        model.addAttribute("attachments", host.getAttachments());
        return ViewUtil.Views.MANAGEHOSTATTACHMENT.getViewName();
    }

    /**
     * @param newAttachment
     * @param hostId
     * @param model
     * @return
     */
    @PostMapping("/manage-attachment-{hostId}")
    public String saveAttachment(@Valid Attachment newAttachment,
                                 @RequestParam("fileContent") MultipartFile fileContent,
                                 @PathVariable int hostId, ModelMap model) {

        try {
            Host host = hostService.findHostByHostId(hostId);
            newAttachment.setHost(host);
            newAttachment.setFileContent(fileContent.getBytes());
            newAttachment.setCreationDate(new Date());
            newAttachment.setFileType(
                    fileContent.getOriginalFilename().substring(fileContent.getOriginalFilename().lastIndexOf(".") + 1));

            attachmentService.saveAttachment(newAttachment);

            Attachment attachment = new Attachment();
            attachment.setHost(host);
            model.addAttribute("attachment", attachment);

            Host updatedHost = hostService.findHostByHostId(hostId);
            model.addAttribute("attachments", updatedHost.getAttachments());

        } catch (IOException e) {
            logger.error("I/O exception to save a host attachment due to: " + e.getMessage());
        }

        model.addAttribute("success", "Attachment registered successfully");
        return ViewUtil.Views.MANAGEHOSTATTACHMENT.getViewName();
    }

    /**
     * Removes an attachment based on the given Id
     *
     * @param attachmentId
     * @return
     */
    @RequestMapping(value = {"/delete-attachment-{attachmentId}"}, method = RequestMethod.GET)
    public String deleteAttachment(@PathVariable int attachmentId) {
        attachmentService.deleteAttachmentById(attachmentId);
        return "redirect:/host/hostList";
    }
}
