package com.nhnacademy.minidorray_gateway.domain.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCreateDto {
    private String taskName;
    private String taskDescription;
    private String taskStatus;
    private Long projectId;
    private String userId;
}
