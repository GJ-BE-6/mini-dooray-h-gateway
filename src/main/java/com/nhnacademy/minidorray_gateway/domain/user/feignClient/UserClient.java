package com.nhnacademy.minidorray_gateway.domain.user.feignClient;

import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-api", url = "http://localhost:8081/api/accounts")
public interface UserClient {

    @PostMapping("/user")
    boolean registerUser(@RequestBody User user);

    @PostMapping("/auth")
    User loginUser(@RequestBody User user); // 이거는 스프링 시큐리티로 할거라 아직보류
}
