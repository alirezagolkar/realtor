package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by asus on 12/21/2017.
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface EmailService {
    void sendMail(User user);
}
