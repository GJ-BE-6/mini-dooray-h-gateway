package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.dto.TagDTO;
import com.nhnacademy.minidorray_gateway.domain.project.dto.TagResponseDTO;
import com.nhnacademy.minidorray_gateway.domain.project.dto.TaskTagDTO;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import io.swagger.v3.oas.annotations.Operation;
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



    @Operation(summary = "프로젝트 태그 추가 페이지 이동", description = "프로젝트 태그 추가페이지로 이동합니다")
    @GetMapping("/tags")
    public String getTagsAddPage(@RequestParam("projectId") Long projectId, Model model){
        model.addAttribute("projectId", projectId);
        return "projectTagAdd";
    }

    @Operation(summary = "프로젝트 태그를 생성", description = "프로젝트 태그를 생성하는 기능을 수행합니다")
    @PostMapping("/tags")
    public String createTag(@ModelAttribute TagDTO tag) {
        tagFeignClient.createTag(tag);
        return "redirect:/projects/project/" + tag.getProjectId();
    }

    @Operation(summary = "프로젝트 태그를 삭제", description = "프로젝트 태그를 삭제하는 기능을 수행합니다")
    @DeleteMapping("/tags/{tagId}")
    public String deleteTag(@PathVariable Long tagId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId= auth.getName();
        tagFeignClient.deleteTag(tagId);
        return "redirect:/projects/"+userId;
    }

    @Operation(summary = "프로젝트 태그를 수정하는 페이지로 이동", description = "프로젝트 태그를 수정하는 페이지로 이동하는 기능을 수행합니다")
    @GetMapping("/tags/update")
    public String updateTagPage(@RequestParam("tagId") Long tagId, Model model){
        TagResponseDTO tagResponseDTO = tagFeignClient.getTagById(tagId);
        model.addAttribute("tag", tagResponseDTO);
        return "tagUpdate";
    }


    @Operation(summary = "프로젝트 태그를 수정", description = "프로젝트 태그를 수정하는 기능을 수행합니다")
    @PutMapping("/tags/{tagId}")
    public String updateTag(@PathVariable Long tagId, @ModelAttribute TagDTO tag) {
        TagDTO tagDTO = tagFeignClient.updateTag(tagId, tag);
        return "redirect:/projects/project/" + tagDTO.getProjectId();
    }

    @Operation(summary = "테스크에 태그를 설정", description = "테스크에 태그를 설정하는 기능을 수행합니다")
    @PostMapping("/tasks/tag")
    public String setTagToTask(@RequestParam("tagId") Long tagId, @RequestParam("taskId") Long taskId) {
        tagFeignClient.setTagToTask(new TaskTagDTO(tagId, taskId));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId= auth.getName();
        return "redirect:/projects/"+userId;
        // todo: task 세부 페이지로 리다이렉트 수정
        // redirect:/tasks/"+taskId;
    }

    @Operation(summary = "테스크 태그를 삭제", description = "테스트 태그를 삭제하는 기능을 수행합니다")
    @DeleteMapping("/tasks/tag")
    public String deleteTaskToTask(@RequestParam("tagId") Long tagId, @RequestParam("taskId") Long taskId) {
        tagFeignClient.deleteTagFromTask(new TaskTagDTO(tagId, taskId));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId= auth.getName();
        return "redirect:/projects/"+userId;
    }

}

