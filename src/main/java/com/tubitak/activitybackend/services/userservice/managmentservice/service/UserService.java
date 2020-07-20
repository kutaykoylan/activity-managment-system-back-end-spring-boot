package com.tubitak.activitybackend.services.userservice.managmentservice.service;

import com.tubitak.activitybackend.services.userservice.data.entitity.User;
import com.tubitak.activitybackend.services.userservice.data.repository.UserRepository;
import com.tubitak.activitybackend.services.userservice.managmentservice.service.contract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  implements IUserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(((user)->{
            users.add((User)user);
        }));
        return users;
    }

    @Override
    public User saveOrUpdateUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean isUserExist(User user) {
        return false;
    }
}
