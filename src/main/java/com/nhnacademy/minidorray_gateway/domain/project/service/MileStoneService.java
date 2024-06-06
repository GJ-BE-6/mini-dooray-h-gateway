package com.nhnacademy.minidorray_gateway.domain.project.service;

import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import com.nhnacademy.minidorray_gateway.domain.project.model.MileStone;
import org.springframework.stereotype.Service;

@Service
public class MileStoneService {
    private final TaskClient taskClient;

    public MileStoneService(TaskClient taskClient) {
        this.taskClient = taskClient;
    }

    public void createMilestone(MileStone milestone) {
        taskClient.createMilestone(milestone);
    }

    public void updateMilestone(Long milestoneId, MileStone milestone) {
        taskClient.updateMilestone(milestoneId, milestone);
    }

    public void deleteMilestone(Long milestoneId) {
        taskClient.deleteMilestone(milestoneId);
    }
}
