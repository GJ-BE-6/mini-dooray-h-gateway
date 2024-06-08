package com.nhnacademy.minidorray_gateway.domain.user.service;

import com.nhnacademy.minidorray_gateway.domain.user.feignClient.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.nhnacademy.minidorray_gateway.domain.user.model.User user = userService.getUserByUserId(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getId(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}



