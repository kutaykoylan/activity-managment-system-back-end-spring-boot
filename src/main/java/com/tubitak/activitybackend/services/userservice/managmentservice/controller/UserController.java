package com.tubitak.activitybackend.services.userservice.managmentservice.controller;

import com.tubitak.activitybackend.common.response.Response;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import com.tubitak.activitybackend.services.userservice.common.service.CustomUserDetailsManager;
import com.tubitak.activitybackend.services.userservice.managmentservice.dto.UserDTO;
import com.tubitak.activitybackend.services.userservice.managmentservice.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final CustomUserDetailsManager userService;
    private final IUserMapper userMapper;

    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody UserDTO userDTO) {
        if (userDTO.equals(null)) {
            return new ResponseEntity<>(new Response("Empty user can't be created!"), HttpStatus.BAD_REQUEST);
        } else {
            User user = userMapper.mapToEntity(userDTO);
            userService.createUser(user);
            return new ResponseEntity<>(new Response("User created successfully!"), HttpStatus.OK);
        }
    }

    @PatchMapping("/password")
    public ResponseEntity<Response> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        if (oldPassword.equals(newPassword)) {
            return new ResponseEntity<>(new Response("New password can't be the same as old one"), HttpStatus.BAD_REQUEST);
        } else {
            userService.changePassword(oldPassword, newPassword);
            return new ResponseEntity<>(new Response("Password changed successfully!"), HttpStatus.OK);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<Response> updateUser(@RequestBody UserDTO userDTO) {
        Optional<UserDTO> optionalUser = Optional.ofNullable(userDTO);
        if (optionalUser.isPresent()) {
            User user = userMapper.mapToEntity(userDTO);
            userService.updateUser(user);
            return new ResponseEntity<>(new Response("User updated successfully!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Response("Empty user can't be updated!"), HttpStatus.BAD_REQUEST);
        }
    }


}
