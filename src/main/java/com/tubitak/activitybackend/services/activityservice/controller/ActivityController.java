package com.tubitak.activitybackend.services.activityservice.controller;

import com.tubitak.activitybackend.services.activityservice.dto.ActivityDTO;
import com.tubitak.activitybackend.services.activityservice.exception.ActivityNotFoundException;
import com.tubitak.activitybackend.common.exception.BadRequestException;
import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.common.response.Response;
import com.tubitak.activitybackend.services.activityservice.mapper.IActivityMapper;
import com.tubitak.activitybackend.services.activityservice.service.contract.IActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    private IActivityService activityService;
    private IActivityMapper activityMapper;

    @Autowired
    public ActivityController(IActivityService activityService, IActivityMapper activityMapper) {
        this.activityService = activityService;
        this.activityMapper = activityMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addActivity(@RequestBody @Valid ActivityDTO activityDTO) {
        if (activityDTO == null) {
            throw new BadRequestException("You send an empty activity");
        } else {
           // System.out.println(activityDTO);
            Activity activity = activityMapper.mapToEntity(activityDTO);
            activity.setId(activityDTO.getId());
            activityService.saveOrUpdate(activity);
            Response response = new Response("Activity is added successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @GetMapping("/numberOfPages")
    public ResponseEntity<Integer> getNumberOfPages(@RequestParam int pageSize){
        return new ResponseEntity<>(activityService.getNumberOfPage(pageSize),HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/pages/{pageNumber}")
    public ResponseEntity<Page<Activity>> getActivitiesOfSpecificPage(@PathVariable String pageNumber, @RequestParam int numberOfActivities) {
        int pageNumberInt = Integer.parseInt(pageNumber);
        Page<Activity> activities = activityService.getWantedPageOfCards(pageNumberInt, numberOfActivities);
        return new ResponseEntity<>(activities,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/allActivities")
    public ResponseEntity<List<ActivityDTO>> getAllActivities(){
        List<ActivityDTO> activityDTOS=activityMapper.mapToDto(activityService.getAll());
        return new ResponseEntity<>(activityDTOS,HttpStatus.OK);

    }

    @GetMapping("/allActivities/{id}")
    public ResponseEntity<Activity> getActivityByID(@PathVariable String id) throws ActivityNotFoundException{
        long idLong = Long.parseLong(id);
        Optional<Activity> activity = Optional.ofNullable(activityService.getById(idLong));
        if(activity.isPresent()){
            return new ResponseEntity<>(activity.get(), HttpStatus.OK);
        }else{
            throw new ActivityNotFoundException("Activity with that ID couldn't found!");
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<Response> updateActivity(@RequestBody @Valid ActivityDTO activityDTO){
        Optional<Activity> activity=Optional.ofNullable(activityService.getById(activityDTO.getId()));
        if (activity.isPresent()) {
            activity.get().setAllData(activityDTO.getTitle(),activityDTO.getDetails(),activityDTO.getLocationLat(),activityDTO.getLocationLng(),activityDTO.getStartDate(),activityDTO.getEndDate());
            activity.get().setId(activityDTO.getId());
            activityService.saveOrUpdate(activity.get());
            return new ResponseEntity<>(new Response("Activity is updated successfully"), HttpStatus.OK);
        }else {
            throw new BadRequestException("There is no activity with this id");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteActivity(@RequestBody @Valid ActivityDTO activityDTO){
        Optional<Activity> activity = Optional.ofNullable(activityService.getByAllDetails(activityMapper.mapToEntity(activityDTO)));
        if(activity.isPresent()){
            activityService.delete(activity.get().getId());
            return new ResponseEntity<>(new Response("Activity is deleted successfully"), HttpStatus.OK);
        }else{
            throw new BadRequestException("There is no activity with this id");
        }
    }
}

