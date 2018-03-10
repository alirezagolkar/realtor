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
 * Created by asus on 7/18/2017.
 */
@Repository("attachmentDao")
public class AttachmentDaoImpl extends AbstractDao<Integer, Attachment> implements AttachmentDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void save(Attachment attachment) throws AttachmentDaoException {
        persist(attachment);
    }

    @Override
    public List<Attachment> findAttachmentsByHostId(int hostId) throws AttachmentDaoException {
        try {
//            Criteria criteria = createEntityCriteria().addOrder(Order.asc("creationDate"));
//            criteria.add(Restrictions.eq("HostId", hostId));
//            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            Query query =  getSession().getNamedQuery(Attachment.GET_ATTACHMENTS_BY_HOST_ID);
            query.setInteger("hostId", hostId);
            return query.list();
        }
        catch (QueryException e)
        {
            throw new HostDaoException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Attachment findAttachmentById(int attachmentId) throws AttachmentDaoException {
        logger.info("accessFileId : {}", attachmentId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", attachmentId));
        return  (Attachment)crit.uniqueResult();
    }

    @Override
    public void delete(int attachmentId) throws AttachmentDaoException {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("id", attachmentId));
            Attachment attachment = (Attachment) criteria.uniqueResult();
            if (attachment != null)
                delete(attachment);
        }
        catch (QueryException e)
        {
            throw new AttachmentDaoException(e.getMessage(), e.getCause());
        }
    }


}
