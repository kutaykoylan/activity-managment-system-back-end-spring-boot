package com.tubitak.activitybackend.services.mailservice.mapper;

import com.tubitak.activitybackend.services.mailservice.dto.UserDTO;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapperForMailService {

    UserDTO mapToDto(User user);

    List<UserDTO> mapToDto(List<User> userList);

    User mapToEntity(UserDTO userDTO);

    List<User> mapToEntity(List<UserDTO> userDTOList);

}
