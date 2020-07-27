package com.tubitak.activitybackend.services.qrcodeservice.service;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.activityservice.data.repository.ActivityRepository;
import com.tubitak.activitybackend.services.mailservice.util.InformationStringGenerator;
import com.tubitak.activitybackend.services.mailservice.util.QRCodeGenerator;
import com.tubitak.activitybackend.services.qrcodeservice.service.contract.IQRCodeService;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import com.tubitak.activitybackend.services.userservice.common.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QRCodeService implements IQRCodeService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    @Override
    public BufferedImage generateQRCode(User user, Activity activity) {

        Optional<User> userToSend = userRepository.findByUsername(user.getUsername());
        Optional<Activity> activityThatRegistered = activityRepository.findById(activity.getId());
        if (userToSend.isPresent() && activityThatRegistered.isPresent()) {
            try {
                BufferedImage bufferedImage = QRCodeGenerator.generateQRCodeImage(InformationStringGenerator.getRegistrationInformation(userToSend.get(), activityThatRegistered.get()));
                return bufferedImage;
            } catch (Exception e) {
                return null;
            }

        }else
        return null;
    }
}
