package com.tubitak.activitybackend.services.usersactivityservice.data;

import com.tubitak.activitybackend.common.data.BaseEntity;
import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_activities")
public class UsersActivity extends BaseEntity {
    @OneToOne//(cascade=CascadeType.ALL)
    private User userID;

    @OneToOne//(cascade= CascadeType.ALL)
    private Activity activityID;
}
