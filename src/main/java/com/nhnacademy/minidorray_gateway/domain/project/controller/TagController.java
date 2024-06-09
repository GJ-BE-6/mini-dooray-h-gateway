package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.dto.TagDTO;
import com.nhnacademy.minidorray_gateway.domain.project.dto.TaskTagDTO;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TagController {

    @Autowired
    private TaskClient tagFeignClient;


    @GetMapping("/tags")
    public String getTagsAddPage(){
        return "projectTagAdd";
    }

    @PostMapping("/tags")
    public String createComment(@ModelAttribute TagDTO tag) {
        tagFeignClient.createTag(tag);
        return "redirect:/projects/"+tag.getProjectId();
    }

    // 태그 목록 가져오기
    @GetMapping("/tags/{projectId}")
    public ResponseEntity<List<TagDTO>> getTagByProjectId(@PathVariable("projectId") Long projectId) {
        List<TagDTO> resp = tagService.getTagsByProjectId(projectId).stream()
                .map(tag -> new TagDTO(tag.getProject().getId(), tag.getName()))
                .toList();
        return ResponseEntity.ok(resp);
    }

    // 태그 선택
    @PostMapping("/task-tags")
    public ResponseEntity<Void> setTagToTask(@RequestBody TaskTagDTO taskTagDTO) {
        tagService.setTagToTask(taskTagDTO.getTaskId(), taskTagDTO.getTagId());
        return ResponseEntity.noContent().build();
    }

    // 태그 삭제
    @DeleteMapping("/task-tags")
    public ResponseEntity<Void> deleteTagFromTask(@RequestBody TaskTagDTO taskTagDTO) {
        tagService.deleteTagFromTask(taskTagDTO.getTaskId(), taskTagDTO.getTagId());
        return ResponseEntity.noContent().build();
    }
}

