package com.nhnacademy.minidorray_gateway.domain.project.controller;

import com.nhnacademy.minidorray_gateway.domain.project.dto.ProjectDto;
import com.nhnacademy.minidorray_gateway.domain.project.dto.ProjectMemberDto;
import com.nhnacademy.minidorray_gateway.domain.project.dto.TaskDto;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import com.nhnacademy.minidorray_gateway.domain.project.model.Project;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private TaskClient projectFeignClient;


    @Operation(summary = "멤버 추가 페이지 이동", description = "멤버 추가페이지로 이동합니다")
    @GetMapping("/members")
    public String getMemberAddPage(){
        return "projectMemberAdd";
    }


    @Operation(summary = "프로젝트 추가페이지 이동", description = "프로젝트 추가페이지로 이동합니다")
    @GetMapping("/users/{userId}")
    public String getProjectAddPage(@PathVariable String userId, Model model){
        model.addAttribute("userId", userId);
        return "projectAdd";
    }

    @Operation(summary = "프로젝트 생성", description = "프로젝트 생성 기능을 수행합니다")
    @PostMapping("/users/{userId}")
    public String createProject(@PathVariable String userId, Model model, @ModelAttribute Project project){
        model.addAttribute("userId", userId);
        projectFeignClient.createProject(userId, project);
        return "redirect:/projects/" + userId;
    }


    @Operation(summary = "프로젝트 리스트 가져오기", description = "유저아이디와 관련된 프로젝트 리스트를 가져옵니다")
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






    @Operation(summary = "프로젝트 뷰 기능", description = "프로젝트 뷰 기능을 수행합니다")
    @GetMapping("/project/{projectId}")
    public String getProject(@PathVariable Long projectId, Model model) {
        ProjectDto project = projectFeignClient.getProjectById(projectId);
        List<ProjectMemberDto>memberList=projectFeignClient.getMembers(projectId);
        List<TaskDto>taskList=projectFeignClient.getTasks(projectId);

        model.addAttribute("project", project);
        model.addAttribute("member", memberList);
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