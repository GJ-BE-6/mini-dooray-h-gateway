package com.nhnacademy.minidorray_gateway.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentDTO {
    private String userId;
    private String content;
}
