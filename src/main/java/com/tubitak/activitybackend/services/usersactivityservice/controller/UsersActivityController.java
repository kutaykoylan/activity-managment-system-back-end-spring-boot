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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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


    @GetMapping("/users/{id}")
    public ResponseEntity<List<UserInformationDTO>> getUsersOfActivity(@PathVariable String id){
        Activity activity = new Activity();
        activity.setId(Long.parseLong(id));
        return new ResponseEntity<>(userInformationMapper.mapToDto(usersActivityService.getUsersByActivityID(activity)),HttpStatus.OK);
    }
    @GetMapping("/activities/{username}")
    public ResponseEntity<List<ActivityInformationDTO>> getActivitiesOfUser(@PathVariable String username){
        User user =  new User();
        user.setUsername(username);
        return new ResponseEntity<>(activityInformationMapper.mapToDto(usersActivityService.getActivitiesByUserID(user)),HttpStatus.OK);

    }
    @GetMapping("/numberOfRegistrationByDates/{activityID}")
    public ResponseEntity<Map<LocalDate,Integer>> getNumberOfRegistrationTillToday(@PathVariable String activityID){
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(Long.parseLong(activityID));
        return new ResponseEntity<>(usersActivityService.getNumberOfRegistration(activityDTO),HttpStatus.OK);
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
    @DeleteMapping("/deleteActivitiesRegistrations/{activityID}")
    public ResponseEntity<Response> deleteRegistrationOfActivities(@PathVariable String  activityID){
        if ( activityID.equals("") || activityID.equals(null) ) {
            throw new BadRequestException("You send an empty request body!");
        } else {
            Activity activity = new Activity();
            activity.setId(Long.parseLong(activityID));
            usersActivityService.deleteActivitySRegistrations(activity);
            return new ResponseEntity<>(new Response("Registration of Activity deleted his/her registration to the activity successfully!"), HttpStatus.OK);
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
