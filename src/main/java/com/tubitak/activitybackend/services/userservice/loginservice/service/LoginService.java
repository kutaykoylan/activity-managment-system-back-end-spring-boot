package com.tubitak.activitybackend.services.userservice.loginservice.service;

import com.tubitak.activitybackend.services.userservice.common.util.JwtUtil;
import com.tubitak.activitybackend.services.userservice.loginservice.network.LoginRequest;
import com.tubitak.activitybackend.services.userservice.loginservice.network.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    @Value(value = "${spring.security.secretKey}")
    private String secretKey;

    private final DaoAuthenticationProvider authenticationProvider;

    public LoginResponse authenticate(final LoginRequest loginRequest) {

        Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        try {
            Authentication user = authenticationProvider.authenticate(usernamePasswordAuthenticationToken);
            String token = JwtUtil.generateToken(user, secretKey, 15);
            return new LoginResponse(token);
        } catch (AuthenticationException e) {
            return new LoginResponse("");
        }
    }
}