package com.tubitak.activitybackend.services.userservice.managmentservice.service.contract;


import com.tubitak.activitybackend.services.userservice.data.entitity.User;

import java.util.List;

public interface IUserService {
    List<User> listAll();
    User saveOrUpdateUser(User user);
    User getById(String id);
    void delete(Long id);
    boolean isUserExist(User user);
}
