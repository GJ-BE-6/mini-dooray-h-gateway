package com.nhnacademy.minidorray_gateway.domain.user.controller;

import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import com.nhnacademy.minidorray_gateway.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "홈화면으로 이동하는 기능을 수행", description = "홈화면으로 이동하는 기능을 수행합니다")
    @GetMapping("/")
    public String home() {
        return "redirect:/home";
    }


    @Operation(summary = "로그인 화면으로 이동하는 기능을 수행", description = "로그인 화면으로 이동하는 기능을 수행합니다")
    @GetMapping("/auth")
    public String login() {
        return "login";
    }


    @Operation(summary = "회원가입으로 이동하는 기능을 수행", description = "회원가입으로 이동하는 기능을 수행합니다")
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @Operation(summary = "로그인하는 기능을 수행", description = "로그인하는 기능을 수행합니다")
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

    @Operation(summary = "회원가입하는 기능을 수행", description = "회원가입하는 기능을 수행합니다")
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    @Operation(summary = "에러페이지로 이동하는 기능", description = "에러페이지로 이동하는 기능을 수행합니다")
    @GetMapping("/auth/error")
    public String accessDenied() {
        return "access_denied";
    }

    @Operation(summary = "유저 프로필을 보여주는 기능을 수행", description = "유저 프로필을 보여주는 기능을 수행합니다")
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
