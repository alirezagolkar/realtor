package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Attachment;
import exception.BusinessException;

import java.util.List;

/**
 * Attachment Service
 */
public interface AttachmentService {

    /**
     * Saves an attachment
     *
     * @param attachment
     */
    void saveAttachment(Attachment attachment);

    /**
     * Loads attachments by a given host Id
     *
     * @param hostId
     * @return
     */
    List<Attachment> findAttachmentsByHostId(int hostId);

    /**
     * Loads attachments by a given attachment Id
     *
     * @param attachmentId
     * @return
     */
    Attachment findAttachmentById(int attachmentId);

    /**
     * Deletes an attachment
     *
     * @param attachmentId
     */
    void deleteAttachmentById(int attachmentId);

}
