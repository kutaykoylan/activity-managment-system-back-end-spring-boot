package com.tubitak.activitybackend.services.usersactivityservice.mapper;

import com.tubitak.activitybackend.services.activityservice.data.entity.Activity;
import com.tubitak.activitybackend.services.activityservice.dto.ActivityDTO;
import com.tubitak.activitybackend.services.usersactivityservice.data.UsersActivity;
import com.tubitak.activitybackend.services.usersactivityservice.dto.UsersActivityDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUsersActivityMapper {

    UsersActivityDTO mapToDto(UsersActivity usersActivity);

    List<UsersActivityDTO> mapToDto(List<UsersActivity> usersActivityList);

    UsersActivity mapToEntity(UsersActivityDTO usersActivityDTO);

    List<UsersActivity> mapToEntity(List<UsersActivityDTO> usersActivityDTOList);

}