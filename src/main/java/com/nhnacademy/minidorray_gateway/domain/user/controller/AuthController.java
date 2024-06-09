package com.nhnacademy.minidorray_gateway.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Operation(summary = "세션을 체크하는 기능을 수행", description = "세션을 체크하는 기능을 수행합니다")
    @GetMapping("/check-login")
    public Map<String, Boolean> checkLogin(HttpSession session) {
        Map<String, Boolean> response = new HashMap<>();
        Boolean isLoggedIn = session.getAttribute("SPRING_SECURITY_CONTEXT") != null;
        response.put("loggedIn", isLoggedIn);
        return response;
    }
}
