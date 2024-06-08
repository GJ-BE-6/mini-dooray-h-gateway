package com.nhnacademy.minidorray_gateway.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectMemberDto {
    private String userId;
    private Long projectId;
    private String role;
}
