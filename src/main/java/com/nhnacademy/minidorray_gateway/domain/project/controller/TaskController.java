package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.dto.*;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller()
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskClient taskFeignClient;

    @Operation(summary = "테스크 리스트를 가져오는 기능", description = "프로젝트 태스크리스트를 가져오는 기능을 수행합니다")
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

    @Operation(summary = "프로젝트 테스크를 추가하는 페이지로 이동", description = "프로젝트 테스크를 추가하는 페이지로 이동하는 기능을 수행합니다")
    @GetMapping("/task/{projectId}")
    public String getAddTasks(@PathVariable("projectId") Long projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "projectTaskAdd"; // The view name for adding a task
    }

    @Operation(summary = "프로젝트 테스크를 생성", description = "프로젝트 테스크를 생성하는 기능을 수행합니다")
    @PostMapping("/{projectId}")
    public String createTask(@PathVariable Long projectId, @ModelAttribute TaskCreateDto task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        task.setUserId(userId);
        taskFeignClient.createTask(task);


        return "redirect:/projects/project/"+projectId;
    }


    // Task 수정 페이지
    @Operation(summary = "프로젝트 테스크를 수정하는 페이지로 이동", description = "프로젝트 테스크를 수정하는 페이지로 이동하는 기능을 수행합니다")
    @GetMapping("/task/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        TaskDto task = taskFeignClient.getTask(id);
        model.addAttribute("task", task);
        return "taskEdit"; // 수정 폼 페이지 이름
    }


    @Operation(summary = "프로젝트 테스크를 수정", description = "프로젝트 테스크를 수정하는 기능을 수행합니다")
    @PutMapping("/task/edit/{id}")
    public String updateTask(@ModelAttribute TaskDto task, @PathVariable Long id) {

        TaskDto oldTask = taskFeignClient.getTask(id);

        oldTask.setTaskName(task.getTaskName());
        oldTask.setTaskDescription(task.getTaskDescription());
        oldTask.setTaskStatus(task.getTaskStatus());

        taskFeignClient.updateTask(oldTask);

        return "redirect:/projects/project/"+task.getProjectId();
    }

    // Task 삭제
    @Operation(summary = "프로젝트 테스크를 삭제", description = "프로젝트 테스크를 삭제하는 기능을 수행합니다")
    @DeleteMapping("/task/{id}")
    public String deleteTask(@PathVariable Long id) {
        TaskDto task = taskFeignClient.getTask(id);

        taskFeignClient.deleteTask(id);

        return "redirect:/projects/project/"+task.getProjectId();
    }
}