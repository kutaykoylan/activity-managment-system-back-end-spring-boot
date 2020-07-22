package com.tubitak.activitybackend.services.userservice.common.data.repository;

import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);

    boolean existsByUsername(String username);
}
