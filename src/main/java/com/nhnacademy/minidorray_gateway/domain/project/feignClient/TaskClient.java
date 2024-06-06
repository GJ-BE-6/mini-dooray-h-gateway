package com.nhnacademy.minidorray_gateway.domain.project.feignClient;

import com.nhnacademy.minidorray_gateway.domain.project.model.*;
import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "task-api", url = "http://localhost:8082/api/task")
public interface TaskClient {

    @GetMapping("/projects")
    List<Project> getProjects();

    @GetMapping("/projects/{id}")
    Project getProject(@PathVariable("id") Long id);

    @PostMapping("/projects")
    void createProject(@RequestBody Project project);

    @PostMapping("/projects/{projectId}/members")
    void addMember(@PathVariable("projectId") Long projectId, @RequestBody User user);

    @PutMapping("/projects/{projectId}/tags")
    void setTags(@PathVariable("projectId") Long projectId, @RequestBody List<Tag> tags);

    @PutMapping("/projects/{projectId}/milestones")
    void setMilestones(@PathVariable("projectId") Long projectId, @RequestBody List<MileStone> milestones);

    @GetMapping("/tasks")
    List<Task> getTasks();

    @PostMapping("/tasks")
    void createTask(@RequestBody Task task);

    @PutMapping("/tasks/{taskId}")
    void updateTask(@PathVariable("taskId") Long taskId, @RequestBody Task task);

    @DeleteMapping("/tasks/{taskId}")
    void deleteTask(@PathVariable("taskId") Long taskId);

    @PostMapping("/tags")
    void createTag(@RequestBody Tag tag);

    @PutMapping("/tags/{tagId}")
    void updateTag(@PathVariable("tagId") Long tagId, @RequestBody Tag tag);

    @DeleteMapping("/tags/{tagId}")
    void deleteTag(@PathVariable("tagId") Long tagId);

    @PostMapping("/milestones")
    void createMilestone(@RequestBody MileStone milestone);

    @PutMapping("/milestones/{milestoneId}")
    void updateMilestone(@PathVariable("milestoneId") Long milestoneId, @RequestBody MileStone milestone);

    @DeleteMapping("/milestones/{milestoneId}")
    void deleteMilestone(@PathVariable("milestoneId") Long milestoneId);

    @PostMapping("/tasks/{taskId}/comments")
    void createComment(@PathVariable("taskId") Long taskId, @RequestBody Comment comment);

    @PutMapping("/comments/{commentId}")
    void updateComment(@PathVariable("commentId") Long commentId, @RequestBody Comment comment);

    @DeleteMapping("/comments/{commentId}")
    void deleteComment(@PathVariable("commentId") Long commentId);
}