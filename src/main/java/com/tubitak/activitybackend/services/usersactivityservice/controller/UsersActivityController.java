package com.tubitak.activitybackend.services.usersactivityservice.controller;

import com.tubitak.activitybackend.common.exception.BadRequestException;
import com.tubitak.activitybackend.common.response.Response;
import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.usersactivityservice.data.UsersActivity;
import com.tubitak.activitybackend.services.usersactivityservice.dto.*;
import com.tubitak.activitybackend.services.usersactivityservice.mapper.*;
import com.tubitak.activitybackend.services.usersactivityservice.service.contract.IUsersActivityService;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usersActivities")
@CrossOrigin("http://localhost:3000")
public class UsersActivityController {

    private final IUsersActivityService usersActivityService;
    private final IUsersActivityMapper usersActivityMapper;
    private final IUserMapperForUsersActivities userMapper;
    private final IActivityMapperForUsersActivities activityMapper;
    private final IUserInformationMapper userInformationMapper;
    private final IActivityInformationMapper activityInformationMapper;


    @GetMapping("/users")
    public ResponseEntity<List<UserInformationDTO>> getUsersOfActivity(@RequestBody ActivityDTO activityDTO){
        Activity activity =  activityMapper.mapToEntity(activityDTO);
        return new ResponseEntity<>(userInformationMapper.mapToDto(usersActivityService.getUsersByActivityID(activity)),HttpStatus.OK);
    }
    @GetMapping("/activities")
    public ResponseEntity<List<ActivityInformationDTO>> getActivitiesOfUser(@RequestBody UserDTO userDTO){
        User user =  userMapper.mapToEntity(userDTO);
        return new ResponseEntity<>(activityInformationMapper.mapToDto(usersActivityService.getActivitiesByUserID(user)),HttpStatus.OK);

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
            System.out.println(usersActivityDTO);
            UsersActivity usersActivity = usersActivityMapper.mapToEntity(usersActivityDTO);
            usersActivityService.registerActivity(usersActivity);
            return new ResponseEntity<>(new Response("User registered successfully!"), HttpStatus.CREATED);
        }

    }
}
