package com.tubitak.activitybackend.services.qrcodeservice.mapper;

import com.tubitak.activitybackend.services.qrcodeservice.dto.UserDTO;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapperForQRCodeService {

    UserDTO mapToDto(User user);

    List<UserDTO> mapToDto(List<User> userList);

    User mapToEntity(UserDTO userDTO);

    List<User> mapToEntity(List<UserDTO> userDTOList);

}
