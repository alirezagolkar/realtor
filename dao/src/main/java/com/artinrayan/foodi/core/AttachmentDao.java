package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Attachment;
import exceptions.AttachmentDaoException;

import java.util.List;

/**
 * Attachments data access interface
 */
public interface AttachmentDao {

    /**
     * Saves an attachment
     *
     * @param attachment
     * @throws AttachmentDaoException
     */
    void save(Attachment attachment) throws AttachmentDaoException;

    /**
     * Loads attachments by a given host Id
     *
     * @param hostId
     * @return
     * @throws AttachmentDaoException
     */
    List<Attachment> findAttachmentsByHostId(int hostId) throws AttachmentDaoException;

    /**
     * Loads attachments by a given attachment Id
     *
     * @param attachmentId
     * @return
     * @throws AttachmentDaoException
     */
    Attachment findAttachmentById(int attachmentId) throws AttachmentDaoException;

    /**
     * Deletes an attachment
     *
     * @param attachmentId
     * @throws AttachmentDaoException
     */
    void delete(int attachmentId) throws AttachmentDaoException;

}