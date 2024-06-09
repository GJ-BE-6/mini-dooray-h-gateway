package com.nhnacademy.minidorray_gateway.domain.user.controller;

import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import com.nhnacademy.minidorray_gateway.domain.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/home";
    }

    @GetMapping("/auth")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/auth/home")
    public String loginUser() {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        // 인증된 사용자 객체를 UserService를 통해 가져옴
        User user = userService.getUserById(userId);

        // userService.authenticateUser 호출
        userService.authenticateUser(user.getUserId(), user.getUserPassword());

        return "redirect:/home";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/auth/error")
    public String accessDenied() {
        return "access_denied";
    }

    @GetMapping("/profile")
    public String profileUser(Model model) {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        // 인증된 사용자 객체를 UserService를 통해 가져옴
        User user = userService.getUserById(userId);

        // 사용자 정보를 모델에 추가
        model.addAttribute("user", user);

        return "profile";
    }

}
