package com.nhnacademy.minidorray_gateway.domain.user.service;

import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;

    public UserService(RestTemplate restTemplate, PasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
        String url = "http://localhost:8081/api/account/register";
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        restTemplate.postForObject(url, user, String.class);
    }

    public void authenticateUser(String userId, String password) {
        String url = "http://localhost:8081/api/account/authenticate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userId", userId);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        try {
            restTemplate.postForObject(url, request, String.class);
            //return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(String userId) {
        String url = "http://localhost:8081/api/account/" + userId;
        return restTemplate.getForObject(url, User.class);
    }



}
