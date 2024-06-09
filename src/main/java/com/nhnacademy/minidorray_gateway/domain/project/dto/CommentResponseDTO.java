package com.nhnacademy.minidorray_gateway.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentResponseDTO {
    private Long commentId;
    private String comment;
    private String userId;
    private Long taskId;
}
