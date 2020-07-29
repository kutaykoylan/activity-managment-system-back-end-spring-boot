package com.tubitak.activitybackend.services.mailservice.service;

import com.tubitak.activitybackend.common.exception.BadRequestException;
import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.activityservice.data.repository.ActivityRepository;
import com.tubitak.activitybackend.services.mailservice.service.contract.IMailSenderService;
import com.tubitak.activitybackend.services.mailservice.util.InformationStringGenerator;
import com.tubitak.activitybackend.services.mailservice.util.QRCodeGenerator;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import com.tubitak.activitybackend.services.userservice.common.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MailSenderService implements IMailSenderService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    @Override
    public void sendEmailWithAttachment(User user, Activity activity) throws MessagingException, IOException {

        Optional<User> userToSend = userRepository.findByUsername(user.getUsername());
        Optional<Activity> activityThatRegistered = activityRepository.findById(activity.getId());

        if (userToSend.isPresent() && activityThatRegistered.isPresent()) {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(userToSend.get().getEmail());
            helper.setSubject("Welcome to " + activityThatRegistered.get().getTitle());
            helper.setText("Details of the acitivite;\n" + activityThatRegistered.get().getDetails() + "\n Your QR code that symbolize your registration\n");
            File outputfile = new File(userToSend.get().getName() + "_" + activityThatRegistered.get().getTitle());
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = QRCodeGenerator.generateQRCodeImage(InformationStringGenerator.getRegistrationInformation(userToSend.get(), activityThatRegistered.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ImageIO.write(bufferedImage, "jpg", outputfile);

            helper.addAttachment("Your_QR_Code.png", outputfile);

            javaMailSender.send(msg);
        } else {
            throw new BadRequestException("Bad Request");
        }
    }
}
