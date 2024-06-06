package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.model.Comment;
import com.nhnacademy.minidorray_gateway.domain.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/tasks/{taskId}/comments")
    public String createComment(@PathVariable Long taskId, @ModelAttribute Comment comment) {
        commentService.createComment(taskId, comment);
        return "redirect:/tasks/" + taskId;
    }

    @PutMapping("/{commentId}")
    public String updateComment(@PathVariable Long commentId, @ModelAttribute Comment comment) {
        commentService.updateComment(commentId, comment);
        return "redirect:/tasks";
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/tasks";
    }
}