package com.tubitak.activitybackend.services.activityservice.service.contract;


import com.tubitak.activitybackend.services.activityservice.dto.ActivityDTO;
import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IActivityService {
    public List<Activity> findAllByOrderByStartDateAsc();
    public List<Activity> findAllByOrderByStartDateDesc();
    Activity getById(Long id);
    Activity saveOrUpdate(Activity activity);
    void delete(Long id);
    Page<Activity> getWantedPageOfCards(int page, int size);
    int getNumberOfPage(int size);
    Activity getByAllDetails(Activity activity);
    List<Activity> getAll();
}
