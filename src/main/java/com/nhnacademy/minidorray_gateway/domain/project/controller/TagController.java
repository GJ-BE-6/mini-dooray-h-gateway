package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.model.Tag;
import com.nhnacademy.minidorray_gateway.domain.project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/tags")
    public String createComment(@ModelAttribute Tag tag) {
        tagService.createTag(tag);
        return "redirect:/tasks";
    }

    @PutMapping("/tags/{tagId}")
    public String updateComment(@PathVariable("tagId") Long tagId, @ModelAttribute Tag tag) {
        tagService.updateTag(tagId, tag);
        return "redirect:/tasks";
    }

    @DeleteMapping("/tags/{tagId}")
    public String deleteComment(@PathVariable("tagId") Long tagId) {
        tagService.deleteTag(tagId);
        return "redirect:/tasks";
    }
}

