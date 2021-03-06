package com.tubitak.activitybackend.services.usersactivityservice.dto;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersActivityDTO {

    private UserDTO userID;
    private ActivityDTO activityID;
}
