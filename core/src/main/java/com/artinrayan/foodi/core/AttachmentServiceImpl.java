package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.Attachment;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Attachment Service Implementation
 */
@Service("attachmentService")
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentDao attachmentDao;

    /**
     * Saves an attachment
     *
     * @param attachment
     */
    @Override
    public void saveAttachment(Attachment attachment){
        attachmentDao.save(attachment);
    }

    /**
     * Loads attachments by a given host Id
     *
     * @param hostId
     * @return
     */
    public List<Attachment> findAttachmentsByHostId(int hostId){
        return attachmentDao.findAttachmentsByHostId(hostId);
    }

    /**
     * Loads attachments by a given attachment Id
     *
     * @param attachmentId
     * @return
     */
    @Override
    public Attachment findAttachmentById(int attachmentId){
        return attachmentDao.findAttachmentById(attachmentId);
    }

    /**
     * Deletes an attachment
     *
     * @param attachmentId
     */
    @Override
    public void deleteAttachmentById(int attachmentId){
        attachmentDao.delete(attachmentId);

    }

}
