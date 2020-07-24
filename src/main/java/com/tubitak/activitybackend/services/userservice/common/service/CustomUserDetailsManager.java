package com.tubitak.activitybackend.services.userservice.common.service;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.Authority;
import com.tubitak.activitybackend.services.userservice.common.data.entitity.User;
import com.tubitak.activitybackend.services.userservice.common.data.repository.AuthorityRepository;
import com.tubitak.activitybackend.services.userservice.common.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(final UserDetails user) {
        User users = (User) user;
        Optional<Authority> authority = authorityRepository.findById(2L);
        if (authority.isPresent()){
            users.setAuthorities(Set.of(authority.get()));
        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);
    }

    @Override
    public void updateUser(final UserDetails user) {
        User oldUser = (User) loadUserByUsername(user.getUsername());
        User newUser = (User) user;
        newUser.setId(oldUser.getId());
        newUser.setPassword(oldUser.getPassword());
        userRepository.save(newUser);
    }

    @Override
    public void deleteUser(final String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public void changePassword(final String oldPassword, final String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        User user = (User) loadUserByUsername(username);
        if(passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Wrong old password is given!");
        }
    }

    @Override
    public boolean userExists(final String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
