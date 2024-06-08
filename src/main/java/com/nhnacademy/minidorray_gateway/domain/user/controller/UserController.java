package com.nhnacademy.minidorray_gateway.domain.user.controller;


import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import com.nhnacademy.minidorray_gateway.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/account")
    public String getRegister() {
        return "userRegister";
    }

    @PostMapping("/account")
    public String registerUser(@ModelAttribute User user, Model model) {
        if(userService.registerUser(user)){
            return "redirect:/auth";
        }
        return "userLogin";
    }

    @GetMapping("/auth")
    public String getLogin() {

        return "userLogin";
    }

//    @PostMapping("/auth")
//    public String loginUser(@ModelAttribute User user, Model model) {
//        boolean isLoggedIn = accountClient.loginUser(user);
//        if (isLoggedIn) {
//            return "redirect:/projects";
//        }
//        model.addAttribute("error", "Login failed");
//        return "userLogin";
//    }


//
//    @Slf4j
//    @Controller
//    @RequestMapping("/login")
//    public class LoginController {
//
//        @GetMapping
//        public String login(Authentication authentication) {
//            if(Objects.isNull(authentication)) {
//                return "login";
//            }
//            return "redirect:/";
//        }
//
//    }
}