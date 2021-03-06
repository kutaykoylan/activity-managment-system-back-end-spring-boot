package com.tubitak.activitybackend.services.usersactivityservice.repository;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.usersactivityservice.data.UsersActivity;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IUsersActivityRepository extends JpaRepository<UsersActivity,Long> {
    List<UsersActivity> findAllByUserID(User userID);
    List<UsersActivity> findAllByActivityID(Activity activityID);
    Optional<UsersActivity> findByUserIDAndActivityID(User userID,Activity activityID);
    int countByCreationDate(LocalDateTime creationDate);
}
