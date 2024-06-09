package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.dto.CommentDTO;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private TaskClient commentFeignClient;

    @PostMapping("/tasks/{taskId}/comments")
    public String createComment(@PathVariable Long taskId, @ModelAttribute CommentDTO comment) {
        commentFeignClient.createComment(taskId, comment);
        return "redirect:/projects/tasks/" + taskId;
    }

//    @PutMapping("/{commentId}")
//    public String updateComment(@PathVariable Long commentId, @ModelAttribute Comment comment) {
//        commentService.updateComment(commentId, comment);
//        return "redirect:/tasks";
//    }
//
//    @DeleteMapping("/{commentId}")
//    public String deleteComment(@PathVariable Long commentId) {
//        commentService.deleteComment(commentId);
//        return "redirect:/tasks";
//    }
}