package com.tubitak.activitybackend.services.activityservice.data.repository;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
     List<Activity> findAllByOrderByStartDateAsc();
     List<Activity> findAllByOrderByStartDateDesc();
     Optional<Activity> findById(Long id);
}
