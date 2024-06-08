package com.nhnacademy.minidorray_gateway.domain.project.service;

import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import com.nhnacademy.minidorray_gateway.domain.project.model.MileStone;
import com.nhnacademy.minidorray_gateway.domain.project.model.Project;
import com.nhnacademy.minidorray_gateway.domain.project.model.Tag;
import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {


    private final TaskClient taskClient;

    public ProjectService(TaskClient taskClient) {
        this.taskClient = taskClient;
    }



    public List<Project> getProjects(String userId) {
        return taskClient.getProjects(userId);
    }

    public Project getProject(Long id) {
        return taskClient.getProject(id);
    }

    public void createProject(Project project) {
        taskClient.createProject(project);
    }

    public void addMember(Long projectId, User user) {
        taskClient.addMember(projectId, user);
    }

    public void setTags(Long projectId, List<Tag> tags) {
        taskClient.setTags(projectId, tags);
    }

    public void setMilestones(Long projectId, List<MileStone> milestones) {
        taskClient.setMilestones(projectId, milestones);
    }
}
