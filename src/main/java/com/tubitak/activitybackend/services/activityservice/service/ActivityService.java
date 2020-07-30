package com.tubitak.activitybackend.services.activityservice.service;

import com.tubitak.activitybackend.common.response.Response;
import com.tubitak.activitybackend.services.activityservice.dto.ActivityDTO;
import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.activityservice.data.repository.ActivityRepository;
import com.tubitak.activitybackend.services.activityservice.service.contract.IActivityService;
import com.tubitak.activitybackend.services.usersactivityservice.repository.IUsersActivityRepository;
import com.tubitak.activitybackend.services.usersactivityservice.service.contract.IUsersActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityService implements IActivityService {
    private final ActivityRepository activityRepository;
    private final IUsersActivityService usersActivityService;

    @Override
    public List<Activity> findAllByOrderByStartDateAsc() {
        return activityRepository.findAllByOrderByStartDateAsc();
    }

    @Override
    public List<Activity> findAllByOrderByStartDateDesc() {
        return activityRepository.findAllByOrderByStartDateDesc();
    }

    @Override
    public Activity getById(Long id) {
         return activityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    @Override
    public Activity saveOrUpdate(Activity activity) {
        Activity activityTemp;
        Optional<Activity> activityOptional = Optional.ofNullable(activityRepository.findById(activity.getId()).orElse(null));
       if(activityOptional.isPresent()){
           activityTemp=activityOptional.get();
       }else{
           activityTemp= new Activity();
       }
        activityTemp.setAllData(activity.getTitle(),activity.getDetails(),activity.getLocationLat(),activity.getLocationLng(),activity.getStartDate(),activity.getEndDate(),activity.getMaxCapacity());
        System.out.println("this is activity temp\n"+activityTemp);
       return activityRepository.save(activityTemp);
    }

    @Override
    public Activity getByAllDetails(Activity activity) {
        return activityRepository.findById(activity.getId()).get();
    }

    @Override
    public int getNumberOfPage(int size) {
         return activityRepository.findAll(PageRequest.of(0,size)).getTotalPages();
    }

    @Override
    public Page<Activity> getWantedPageOfCards(int page, int size) {
        return activityRepository.findAll(PageRequest.of(page,size,Sort.by("startDate").descending()));
    }

    @Override
    public void delete(Long id) {
        usersActivityService.deleteActivitySRegistrations(activityRepository.findById(id).get());
        activityRepository.deleteById(id);
    }
    /*
    private void deleteFromUsersActivities(String activityID) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromHttpUrl("https://localhost:8080")
                .pathSegment("deleteActivitiesRegistrations",activityID)
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity.delete(uri).build();


        ResponseEntity<Void> response = restTemplate.exchange(requestEntity, Void.class);

    }*/
}
