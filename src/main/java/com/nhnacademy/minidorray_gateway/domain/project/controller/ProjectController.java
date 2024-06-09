package com.nhnacademy.minidorray_gateway.domain.project.controller;

import com.nhnacademy.minidorray_gateway.domain.project.dto.*;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import com.nhnacademy.minidorray_gateway.domain.project.model.Project;
import com.nhnacademy.minidorray_gateway.domain.project.model.ProjectMember;
import com.nhnacademy.minidorray_gateway.domain.project.model.Task;
import com.nhnacademy.minidorray_gateway.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private TaskClient projectFeignClient;

    @Autowired
    private UserService userService;

    @GetMapping("/members")
    public String getMemberAddPage(){
        return "projectMemberAdd";
    }

    @GetMapping("/users/{userId}")
    public String getProjectAddPage(@PathVariable String userId, Model model){
        model.addAttribute("userId", userId);
        return "projectAdd";
    }

    @PostMapping("/users/{userId}")
    public String createProject(@PathVariable String userId, Model model, @ModelAttribute Project project){
        model.addAttribute("userId", userId);
        projectFeignClient.createProject(userId, project);
        return "redirect:/projects/" + userId;
    }


    @GetMapping("/{userId}")
    public String getProjects(@PathVariable String userId, Model model) {
        List<ProjectDto> projects = projectFeignClient.getAllProjectsByUserId(userId);
        model.addAttribute("projects", projects);
        model.addAttribute("userId", userId);
        return "projectMain";
    }

//    @PostMapping("/{userId}")
//    public String createProject(@ModelAttribute Project project, @PathVariable String userId) {
//        projectFeignClient.createProject(userId, project);
//        return "redirect:/projects/"+userId;
//    }

//    @PostMapping("/{projectId}/members")
//    public String addMember(@PathVariable("projectId") Long projectId, @ModelAttribute MemberDto user) {
//        projectFeignClient.addMember(projectId, user);
//        return "redirect:/projects/" + projectId;
//    }






    @GetMapping("/project/{projectId}")
    public String getProject(@PathVariable Long projectId, Model model) {
        ProjectDto project = projectFeignClient.getProjectById(projectId);
        List<ProjectMemberDto>memberList=projectFeignClient.getMembers(projectId);
        List<TaskDto>taskList=projectFeignClient.getTasks(projectId);

        model.addAttribute("project", project);
        model.addAttribute("members", memberList);
        model.addAttribute("tasks", taskList);
        return "projectView";
    }

//
//
//    @PutMapping("/{projectId}/tags")
//    public String setTags(@PathVariable Long projectId, @RequestBody List<Tag> tags) {
//        projectService.setTags(projectId, tags);
//        return "redirect:/projects/" + projectId;
//    }
//
//    @PutMapping("/{projectId}/milestones")
//    public String setMilestones(@PathVariable Long projectId, @RequestBody List<MileStone> milestones) {
//        projectService.setMilestones(projectId, milestones);
//        return "redirect:/projects/" + projectId;
//    }
}