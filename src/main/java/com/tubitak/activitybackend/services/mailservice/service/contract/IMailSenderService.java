package com.tubitak.activitybackend.services.mailservice.service.contract;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IMailSenderService {
    void sendEmailWithAttachment(User user, Activity activity) throws MessagingException, IOException;
}
