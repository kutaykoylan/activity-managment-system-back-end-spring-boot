package com.tubitak.activitybackend.services.userservice.managmentservice.mapper;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import com.tubitak.activitybackend.services.userservice.managmentservice.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    UserDTO mapToDto(User user);

    List<UserDTO> mapToDto(List<User> userList);

    User mapToEntity(UserDTO userDTO);

    List<User> mapToEntity(List<UserDTO> userDTOList);

}
