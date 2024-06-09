package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.dto.*;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskClient taskFeignClient;

    @GetMapping("/{id}")
    public String getTasks(@PathVariable Long id, Model model) {
        TaskDto task = taskFeignClient.getTask(id);
        List<CommentResponseDTO> commentList = taskFeignClient.getComments(id);
        if(commentList==null){
            commentList = new ArrayList<>();
        }
        List<MilestoneDTO>milestoneList= taskFeignClient.getMilestoneByTaskId(id);
        if(milestoneList==null){
            milestoneList = new ArrayList<>();
        }
        List<TagResponseDTO> tagList= taskFeignClient.getTagByProjectId(task.getProjectId());
        if(tagList==null){
            tagList = new ArrayList<>();
        }
        List<TagResponseDTO> settingTagList = taskFeignClient.getTaskTagsByTaskId(id);
        if(settingTagList==null){
            settingTagList = new ArrayList<>();
        }
        model.addAttribute("task", task);
        model.addAttribute("comments", commentList);
        model.addAttribute("milestones", milestoneList);
        model.addAttribute("tags", tagList);
        model.addAttribute("settingTags", settingTagList);
        return "taskView";
    }
    @GetMapping("/task/{projectId}")
    public String getAddTasks(@PathVariable("projectId") Long projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "projectTaskAdd"; // The view name for adding a task
    }

    @PostMapping("/{projectId}")
    public String createTask(@PathVariable Long projectId, @ModelAttribute TaskCreateDto task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        task.setUserId(userId);
        taskFeignClient.createTask(task);


        return "redirect:/projects/project/"+projectId;
    }


    // Task 수정 페이지
    @GetMapping("/task/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        TaskDto task = taskFeignClient.getTask(id);
        model.addAttribute("task", task);
        return "taskEdit"; // 수정 폼 페이지 이름
    }

    @PutMapping("/task/edit/{id}")
    public String updateTask(@ModelAttribute TaskDto task, @PathVariable Long id, @RequestParam("projectId") Long projectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        task.setUserId(userId);
        task.setProjectId(projectId);
        taskFeignClient.updateTask(task);
        return "redirect:/projects/project/"+task.getProjectId();
    }

    // Task 삭제
    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskFeignClient.deleteTask(id);
    }



}