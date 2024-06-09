package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import com.nhnacademy.minidorray_gateway.domain.project.model.Comment;
import com.nhnacademy.minidorray_gateway.domain.project.model.Milestone;
import com.nhnacademy.minidorray_gateway.domain.project.model.Tag;
import com.nhnacademy.minidorray_gateway.domain.project.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class TaskController {

    @Autowired
    private TaskClient taskFeignClient;

    @GetMapping("/tasks/{id}")
    public String getTasks(@PathVariable Long id, Model model) {
        Task task = taskFeignClient.getTask(id);
        List<Comment>commentList= (List<Comment>) taskFeignClient.getComments(id);
        List<Milestone>milestoneList= (List<Milestone>) taskFeignClient.getMilestoneByTaskId(id);
        List<Tag>tagList= (List<Tag>) taskFeignClient.getTaskTagsByTaskId(id);
        model.addAttribute("task", task);
        model.addAttribute("comments", commentList);
        model.addAttribute("milestones", milestoneList);
        model.addAttribute("tags", tagList);
        return "taskView";
    }

    @GetMapping("/projectId")
    public String getAddTasks(@RequestParam("projectId") Long projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "projectTaskAdd"; // The view name for adding a task
    }


    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        taskFeignClient.createTask(task);
        return "redirect:/tasks";
    }


    // Task 수정 페이지
    @PutMapping("/tasks/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskFeignClient.getTask(id);
        model.addAttribute("task", task);
        return "taskEdit"; // 수정 폼 페이지 이름
    }


    // Task 삭제
    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskFeignClient.deleteTask(id);
    }



}