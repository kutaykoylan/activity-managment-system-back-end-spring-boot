package com.tubitak.activitybackend.services.usersactivityservice.service.contract;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.usersactivityservice.data.UsersActivity;
import com.tubitak.activitybackend.services.usersactivityservice.dto.UsersActivityDTO;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsersActivityService {
    List<User> getUsersByActivityID(Activity activityID);
    void registerActivity(UsersActivity usersActivity);
    void withdrawActivity(UsersActivity usersActivity);
    List<Activity> getActivitiesByUserID(User userID);
}
