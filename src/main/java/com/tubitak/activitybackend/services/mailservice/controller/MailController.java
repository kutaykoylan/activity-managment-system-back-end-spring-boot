package com.tubitak.activitybackend.services.mailservice.controller;

import com.tubitak.activitybackend.common.response.Response;
import com.tubitak.activitybackend.services.mailservice.dto.MailDTO;
import com.tubitak.activitybackend.services.mailservice.dto.UserDTO;
import com.tubitak.activitybackend.services.mailservice.mapper.IActivityMapperForMailService;
import com.tubitak.activitybackend.services.mailservice.mapper.IUserMapperForMailService;
import com.tubitak.activitybackend.services.mailservice.service.contract.IMailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final IMailSenderService mailSenderService;
    private final IUserMapperForMailService userMapper;
    private final IActivityMapperForMailService activityMapper;

    @PostMapping("/send")
    public ResponseEntity<Response> sendQrCode(@RequestBody MailDTO mailDTO) throws IOException, MessagingException {
        if (mailDTO==null)
            return new ResponseEntity<>(new Response("Bad request!") , HttpStatus.BAD_REQUEST);
        else{
            mailSenderService.sendEmailWithAttachment(userMapper.mapToEntity(mailDTO.getUserDTO()),activityMapper.mapToEntity(mailDTO.getActivityDTO()));
            return new ResponseEntity<>(new Response("Mail is sent successfully!"),HttpStatus.OK);
        }
    }

}
