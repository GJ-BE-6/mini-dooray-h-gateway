package com.nhnacademy.minidorray_gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private UserStatus userStatus;
    private LocalDateTime lastLoginDate;
}
