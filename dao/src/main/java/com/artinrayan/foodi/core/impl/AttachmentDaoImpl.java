package com.artinrayan.foodi.core.impl;

import com.artinrayan.foodi.core.AbstractDao;
import com.artinrayan.foodi.core.AttachmentDao;
import com.artinrayan.foodi.model.Attachment;
import exceptions.HostDaoException;
import exceptions.AttachmentDaoException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implements attachment dao interface and handle database operations for attachments
 */
@Repository("attachmentDao")
public class AttachmentDaoImpl extends AbstractDao<Integer, Attachment> implements AttachmentDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    /**
     * Saves an attachment
     *
     * @param attachment
     * @throws AttachmentDaoException
     */
    @Override
    public void save(Attachment attachment) throws AttachmentDaoException {
        persist(attachment);
    }

    /**
     * Loads attachments by a given host Id
     *
     * @param hostId
     * @return
     * @throws AttachmentDaoException
     */
    @Override
    public List<Attachment> findAttachmentsByHostId(int hostId) throws AttachmentDaoException {

            Query query =  getSession().getNamedQuery(Attachment.GET_ATTACHMENTS_BY_HOST_ID);
            query.setInteger("hostId", hostId);
            return query.list();
    }

    /**
     * Loads attachments by a given attachment Id
     *
     * @param attachmentId
     * @return
     * @throws AttachmentDaoException
     */
    @Override
    public Attachment findAttachmentById(int attachmentId) throws AttachmentDaoException {
        logger.info("accessFileId : {}", attachmentId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", attachmentId));
        return  (Attachment)crit.uniqueResult();
    }

    /**
     * Deletes an attachment
     *
     * @param attachmentId
     * @throws AttachmentDaoException
     */
    @Override
    public void delete(int attachmentId) throws AttachmentDaoException {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("id", attachmentId));
            Attachment attachment = (Attachment) criteria.uniqueResult();
            if (attachment != null)
                delete(attachment);
    }
}
