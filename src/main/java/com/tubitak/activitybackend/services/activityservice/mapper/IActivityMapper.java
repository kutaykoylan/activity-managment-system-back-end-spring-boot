package com.tubitak.activitybackend.services.activityservice.mapper;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.activityservice.dto.ActivityDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IActivityMapper {

    ActivityDTO mapToDto(Activity activity);

    List<ActivityDTO> mapToDto(List<Activity> activityList);

    Activity mapToEntity(ActivityDTO activityDTO);

    List<Activity> mapToEntity(List<ActivityDTO> studentDTOList);

}
