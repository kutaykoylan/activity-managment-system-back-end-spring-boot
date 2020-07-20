package com.tubitak.activitybackend.services.userservice.managmentservice.controller;

import com.tubitak.activitybackend.services.userservice.managmentservice.service.contract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
