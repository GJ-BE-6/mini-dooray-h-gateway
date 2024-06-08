package com.nhnacademy.minidorray_gateway.domain.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class User {
    private String id;
    private String password;
    private String email;


}