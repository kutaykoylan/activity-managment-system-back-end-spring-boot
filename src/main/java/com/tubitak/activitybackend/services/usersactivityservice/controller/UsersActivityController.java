package com.tubitak.activitybackend.services.usersactivityservice.controller;

import com.tubitak.activitybackend.common.exception.BadRequestException;
import com.tubitak.activitybackend.common.response.Response;
import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.activityservice.dto.ActivityDTO;
import com.tubitak.activitybackend.services.activityservice.mapper.IActivityMapper;
import com.tubitak.activitybackend.services.usersactivityservice.data.UsersActivity;
import com.tubitak.activitybackend.services.usersactivityservice.dto.UsersActivityDTO;
import com.tubitak.activitybackend.services.usersactivityservice.mapper.IUsersActivityMapper;
import com.tubitak.activitybackend.services.usersactivityservice.service.contract.IUsersActivityService;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import com.tubitak.activitybackend.services.userservice.managmentservice.dto.UserDTO;
import com.tubitak.activitybackend.services.userservice.managmentservice.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/usersActivities")
@CrossOrigin("http://localhost:3000")
public class UsersActivityController {

    private IUsersActivityService usersActivityService;
    private IUsersActivityMapper usersActivityMapper;
    private IUserMapper userMapper;
    private IActivityMapper activityMapper;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsersOfActivity(@RequestBody ActivityDTO activityDTO){
        Activity activity =  activityMapper.mapToEntity(activityDTO);
        return new ResponseEntity<>(userMapper.mapToDto(usersActivityService.getUsersByActivityID(activity)),HttpStatus.OK);
    }
    @GetMapping("/activities")
    public ResponseEntity<List<ActivityDTO>> getActivitiesOfUser(@RequestBody UserDTO userDTO){
        User user =  userMapper.mapToEntity(userDTO);
        return new ResponseEntity<>(activityMapper.mapToDto(usersActivityService.getActivitiesByUserID(user)),HttpStatus.OK);

    }
    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteRegistrationOfUser(@RequestBody UsersActivityDTO usersActivityDTO){
        if (usersActivityDTO == null) {
            throw new BadRequestException("You send an empty request body!");
        } else {
            UsersActivity usersActivity = usersActivityMapper.mapToEntity(usersActivityDTO);
            usersActivityService.withdrawActivity(usersActivity);
            return new ResponseEntity<>(new Response("User deleted his/her registration to the activity successfully!"), HttpStatus.OK);
        }

    }
    @PostMapping("/create")
    public ResponseEntity<Response> registerToActivity(@RequestBody UsersActivityDTO usersActivityDTO){
        if (usersActivityDTO == null) {
            throw new BadRequestException("You send an empty request body!");
        } else {
            UsersActivity usersActivity = usersActivityMapper.mapToEntity(usersActivityDTO);
            usersActivityService.registerActivity(usersActivity);
            return new ResponseEntity<>(new Response("User registered successfully!"), HttpStatus.CREATED);
        }

    }
}
