package com.nhnacademy.minidorray_gateway.domain.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
// dto 안만듭니다. 보안 신경안씁니다.
public class User {
    private String username;
    private String password;
    private String email;


}