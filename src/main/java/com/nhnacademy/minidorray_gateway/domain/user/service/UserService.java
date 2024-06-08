package com.nhnacademy.minidorray_gateway.domain.user.service;

import com.nhnacademy.minidorray_gateway.domain.user.feignClient.UserClient;
import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserClient userClient;

    @Autowired
    PasswordEncoder passwordEncoder;


    public boolean registerUser(User user) {
        user.setId(user.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userClient.registerUser(user);
    }

    public User getUserByUserId(String userId) {
        return userClient.getUser(userId);
    }

}
