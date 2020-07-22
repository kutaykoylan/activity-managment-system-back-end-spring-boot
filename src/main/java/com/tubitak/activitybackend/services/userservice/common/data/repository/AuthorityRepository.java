package com.tubitak.activitybackend.services.userservice.common.data.repository;

import com.tubitak.activitybackend.services.userservice.common.data.entitity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
