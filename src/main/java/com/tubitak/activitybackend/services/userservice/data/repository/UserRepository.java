package com.tubitak.activitybackend.services.userservice.data.repository;

import com.tubitak.activitybackend.services.userservice.data.entitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
