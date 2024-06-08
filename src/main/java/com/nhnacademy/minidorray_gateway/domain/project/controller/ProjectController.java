package com.nhnacademy.minidorray_gateway.domain.project.controller;

import com.nhnacademy.minidorray_gateway.domain.project.model.MileStone;
import com.nhnacademy.minidorray_gateway.domain.project.model.Project;
import com.nhnacademy.minidorray_gateway.domain.project.model.Tag;
import com.nhnacademy.minidorray_gateway.domain.project.service.ProjectService;
import com.nhnacademy.minidorray_gateway.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/members")
    public String getMemberAddPage(){
        return "projectMemberAdd";
    }


    @GetMapping("/{userId}")
    public String getProjects(@PathVariable String userId , Model model) {
        List<Project> projects = projectService.getProjects(userId);
        model.addAttribute("projects", projects);
        return "projectMain";
    }

    @PostMapping
    public String createProject(@ModelAttribute Project project) {
        projectService.createProject(project);
        return "redirect:/projects";
    }

    @PostMapping("/{projectId}/members")
    public String addMember(@PathVariable Long projectId, @ModelAttribute User user) {
        projectService.addMember(projectId, user);
        return "redirect:/projects/" + projectId;
    }

    @GetMapping("/{projectId}")
    public String getProject(@PathVariable Long projectId, Model model) {
        Project project = projectService.getProject(projectId);
        model.addAttribute("project", project);
        return "projectView";
    }



    @PutMapping("/{projectId}/tags")
    public String setTags(@PathVariable Long projectId, @RequestBody List<Tag> tags) {
        projectService.setTags(projectId, tags);
        return "redirect:/projects/" + projectId;
    }

    @PutMapping("/{projectId}/milestones")
    public String setMilestones(@PathVariable Long projectId, @RequestBody List<MileStone> milestones) {
        projectService.setMilestones(projectId, milestones);
        return "redirect:/projects/" + projectId;
    }
}