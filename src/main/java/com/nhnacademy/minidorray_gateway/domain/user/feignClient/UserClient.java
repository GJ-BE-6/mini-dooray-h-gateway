package com.nhnacademy.minidorray_gateway.domain.user.feignClient;

import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-api", url = "http://localhost:8081/api/account")
public interface UserClient {

    @PostMapping("/user")
    boolean registerUser(@RequestBody User user);

    @GetMapping("/user")
    User getUser(String userId);

}
