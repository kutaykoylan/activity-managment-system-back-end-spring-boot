package com.tubitak.activitybackend.services.usersactivityservice.mapper;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.usersactivityservice.dto.ActivityDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IActivityMapperForUsersActivities {

    ActivityDTO mapToDto(Activity activity);

    List<ActivityDTO> mapToDto(List<Activity> activityList);

    Activity mapToEntity(ActivityDTO activityDTO);

    List<Activity> mapToEntity(List<ActivityDTO> studentDTOList);

}
