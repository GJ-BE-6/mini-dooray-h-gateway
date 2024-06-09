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

    @GetMapping("/task")
    public String getAddTasks() {

        return "projectTaskAdd";
    }


    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        taskFeignClient.createTask(task);
        return "redirect:/tasks";
    }
//
//    @PutMapping("/{taskId}")
//    public String updateTask(@PathVariable Long taskId, @ModelAttribute Task task) {
//        taskService.updateTask(taskId, task);
//        return "redirect:/tasks";
//    }
//
//    @DeleteMapping("/{taskId}")
//    public String deleteTask(@PathVariable Long taskId) {
//        taskService.deleteTask(taskId);
//        return "redirect:/tasks";
//    }

}