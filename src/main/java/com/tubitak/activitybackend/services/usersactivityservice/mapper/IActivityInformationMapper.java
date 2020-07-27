package com.tubitak.activitybackend.services.usersactivityservice.mapper;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.usersactivityservice.dto.ActivityInformationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IActivityInformationMapper {
    ActivityInformationDTO mapToDto(Activity activity);

    List<ActivityInformationDTO> mapToDto(List<Activity> activityList);

    Activity mapToEntity(ActivityInformationDTO activityDTO);

    List<Activity> mapToEntity(List<ActivityInformationDTO> studentDTOList);
}
