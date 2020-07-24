package com.tubitak.activitybackend.services.userservice.loginservice.network;

import lombok.Getter;

@Getter
public class LoginResponse {

    private final String token;
    private final String authority;

    public LoginResponse(String token, String authority) {
        this.token = token;
        this.authority = authority;
    }

}