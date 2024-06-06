package com.nhnacademy.minidorray_gateway.domain.user.service;

import com.nhnacademy.minidorray_gateway.domain.user.feignClient.UserClient;
import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserClient userClient;




    public boolean registerUser(User user) {
        return userClient.registerUser(user);
    }

    // 스프링 시큐리티로 할꺼라 아직보류
    public User loginUser(User user) {
        return userClient.loginUser(user);
    }
}
