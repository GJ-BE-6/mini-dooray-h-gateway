package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.dto.TagDTO;
import com.nhnacademy.minidorray_gateway.domain.project.dto.TaskTagDTO;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TagController {

    @Autowired
    private TaskClient tagFeignClient;


    @GetMapping("/tags")
    public String getTagsAddPage(@RequestParam("projectId") Long projectId, Model model){
        model.addAttribute("projectId", projectId);
        return "projectTagAdd";
    }

    @PostMapping("/tags")
    public String createTag(@ModelAttribute TagDTO tag) {
        tagFeignClient.createTag(tag);
        return "redirect:/projects/project/" + tag.getProjectId();
    }

    @DeleteMapping("/tags/{tagId}")
    public String deleteTag(@PathVariable Long tagId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId= auth.getName();
        tagFeignClient.deleteTag(tagId);
        return "redirect:/projects/"+userId;
    }

    @PostMapping("/tasks/tag")
    public String setTagToTask(@RequestParam("tagId") Long tagId, @RequestParam("taskId") Long taskId) {
        tagFeignClient.setTagToTask(new TaskTagDTO(tagId, taskId));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId= auth.getName();
        return "redirect:/projects/"+userId;
        // todo: task 세부 페이지로 리다이렉트 수정
        // redirect:/tasks/"+taskId;
    }

    @DeleteMapping("/tasks/tag")
    public String deleteTaskToTask(@RequestParam("tagId") Long tagId, @RequestParam("taskId") Long taskId) {
        tagFeignClient.deleteTagFromTask(new TaskTagDTO(tagId, taskId));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId= auth.getName();
        return "redirect:/projects/"+userId;
    }

}

