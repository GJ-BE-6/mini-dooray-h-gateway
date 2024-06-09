package com.nhnacademy.minidorray_gateway.domain.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Task {

    private Long id;

    private String name;

    private String description;

    private String status;


    private String userId;

}
