package com.tubitak.activitybackend.services.usersactivityservice.mapper;

import com.tubitak.activitybackend.services.usersactivityservice.dto.UserInformationDTO;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserInformationMapper {

    UserInformationDTO mapToDto(User user);

    List<UserInformationDTO> mapToDto(List<User> userList);

    User mapToEntity(UserInformationDTO userDTO);

    List<User> mapToEntity(List<UserInformationDTO> userDTOList);

}