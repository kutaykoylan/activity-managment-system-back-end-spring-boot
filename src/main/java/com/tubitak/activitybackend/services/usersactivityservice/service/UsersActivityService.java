package com.tubitak.activitybackend.services.usersactivityservice.service;

import com.tubitak.activitybackend.common.exception.BadRequestException;
import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.activityservice.data.repository.ActivityRepository;
import com.tubitak.activitybackend.services.usersactivityservice.data.UsersActivity;
import com.tubitak.activitybackend.services.usersactivityservice.dto.UsersActivityDTO;
import com.tubitak.activitybackend.services.usersactivityservice.repository.IUsersActivityRepository;
import com.tubitak.activitybackend.services.usersactivityservice.service.contract.IUsersActivityService;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import com.tubitak.activitybackend.services.userservice.common.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersActivityService implements IUsersActivityService {


    private final IUsersActivityRepository usersActivityRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    @Override
    public List<User> getUsersByActivityID(Activity activityID) {
        Optional<Activity> activity = activityRepository.findById(activityID.getId());
        List<UsersActivity> usersActivities = usersActivityRepository.findAllByActivityID(activity.get());
        List<User> users = new ArrayList<>();
        usersActivities.forEach((element) -> {
            users.add(element.getUserID());
        });
        return users;
    }

    @Override
    public void registerActivity(UsersActivity usersActivity) {
        Optional<User> user = userRepository.findByUsername(usersActivity.getUserID().getUsername());
        Optional<Activity> activity = activityRepository.findById(usersActivity.getActivityID().getId());
        if (user.isPresent() && activity.isPresent() &&
                (usersActivityRepository.findAllByActivityID(activity.get()).size() < Integer.parseInt(activity.get().getMaxCapacity())) &&
                (usersActivityRepository.findByUserIDAndActivityID(user.get(), activity.get()).isEmpty())) {
            usersActivity.setUserID(user.get());
            usersActivity.setActivityID(activity.get());
            usersActivityRepository.save(usersActivity);

        }else{
            throw new BadRequestException("Bad Request!");
        }
    }

    @Override
    public void withdrawActivity(UsersActivity usersActivity) {
        Optional<UsersActivity> optionalUsersActivity = usersActivityRepository.findByUserIDAndActivityID(usersActivity.getUserID(), usersActivity.getActivityID());
        if (optionalUsersActivity.isPresent()) {
            usersActivityRepository.deleteById(optionalUsersActivity.get().getId());
        }else{
            throw new BadRequestException("Bad Request!");
        }
    }

    @Override
    public List<Activity> getActivitiesByUserID(User userID) {
        Optional<User> user = userRepository.findByUsername(userID.getUsername());
        List<UsersActivity> usersActivities = usersActivityRepository.findAllByUserID(user.get());
        List<Activity> activities = new ArrayList<>();
        usersActivities.forEach((element) -> {
            activities.add(element.getActivityID());
        });
        return activities;
    }


}
