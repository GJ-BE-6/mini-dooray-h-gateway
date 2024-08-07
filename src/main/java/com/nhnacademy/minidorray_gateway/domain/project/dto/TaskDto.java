package com.nhnacademy.minidorray_gateway.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class TaskDto {
    private Long taskId;
    private String taskName;
    private String taskDescription;
    private String taskStatus;
    private Long projectId;
    private String userId;

}
