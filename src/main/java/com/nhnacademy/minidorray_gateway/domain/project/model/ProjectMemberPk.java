package com.nhnacademy.minidorray_gateway.domain.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class ProjectMemberPk {

    private String userId;

    private Long projectId;
}
