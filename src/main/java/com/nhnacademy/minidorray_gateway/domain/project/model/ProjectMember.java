package com.nhnacademy.minidorray_gateway.domain.project.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProjectMember {
    private String userId;
    private Long projectId;
    private String role;

}
