package com.tubitak.activitybackend.services.userservice.loginservice.network;

import lombok.Getter;

@Getter
public class LoginResponse {

    private final String token;

    public LoginResponse(final String token) {
        this.token = token;
    }
}