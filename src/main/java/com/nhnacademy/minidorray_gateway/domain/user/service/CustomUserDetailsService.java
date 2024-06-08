package com.nhnacademy.minidorray_gateway.domain.user.service;

import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        String url = "http://localhost:8081/api/account/" + userId;
        com.nhnacademy.minidorray_gateway.domain.user.model.User user = restTemplate.getForObject(url, User.class);
;
        if (user == null) {
            throw new UsernameNotFoundException("User not found with userId: " + userId);
        }

        return org.springframework.security.core.userdetails.User.withUsername(user.getUserId())
                .password(user.getUserPassword())
                .authorities("ROLE_USER")
                .build();
    }
}



