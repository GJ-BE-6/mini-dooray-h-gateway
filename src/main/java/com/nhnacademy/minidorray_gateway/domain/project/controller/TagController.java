package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.dto.TagDTO;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

//    @PutMapping("/tags/{tagId}")
//    public String updateComment(@PathVariable("tagId") Long tagId, @ModelAttribute Tag tag) {
//        tagService.updateTag(tagId, tag);
//        return "redirect:/tasks";
//    }
//
//    @DeleteMapping("/tags/{tagId}")
//    public String deleteComment(@PathVariable("tagId") Long tagId) {
//        tagService.deleteTag(tagId);
//        return "redirect:/tasks";
//    }
}

