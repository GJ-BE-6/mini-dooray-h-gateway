package com.nhnacademy.minidorray_gateway.domain.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Builder
@Setter
public class MilestoneDTO {
    private Long milestoneId;
    private Long projectId;
    private String milestoneName;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private ZonedDateTime startDate;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private ZonedDateTime dueDate;
    @Setter
    private Long taskId;
}
