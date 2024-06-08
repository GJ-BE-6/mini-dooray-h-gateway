package com.nhnacademy.minidorray_gateway.domain.project.dto;

import lombok.Getter;

@Getter
public class TaskCreateDto {
    private String taskName;
    private String taskDescription;
    private String taskStatus;
    private Long projectId;
    private String userId;
}
