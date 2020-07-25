package com.tubitak.activitybackend.services.usersactivityservice.dto;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.activityservice.dto.ActivityDTO;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import com.tubitak.activitybackend.services.userservice.managmentservice.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersActivityDTO {

    private UserDTO userID;
    private ActivityDTO activityID;
}
