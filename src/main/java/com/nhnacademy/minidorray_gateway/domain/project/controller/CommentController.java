package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.dto.CommentDTO;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private TaskClient commentFeignClient;

    @PostMapping("/tasks/{taskId}")
    public String createComment(@PathVariable Long taskId, @ModelAttribute CommentDTO comment) {
        commentFeignClient.createComment(taskId, comment);
        return "redirect:/tasks/" + taskId;
    }



    // 코멘트 삭제
    @DeleteMapping("/{commentId}/{taskId}")
    public String deleteComment(@PathVariable("commentId") Long commentId, @PathVariable("taskId") Long taskId) {
        commentFeignClient.deleteComment(commentId);
        return  "redirect:/tasks/" + taskId;
    }
}