//package com.nhnacademy.minidorray_gateway.domain.project.controller;
//
//
//import com.nhnacademy.minidorray_gateway.domain.project.dto.CommentDTO;
//import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/comments")
//public class CommentController {
//
//    @Autowired
//    private TaskClient commentFeignClient;
//
//    @PostMapping("/tasks/{taskId}/comments")
//    public String createComment(@PathVariable Long taskId, @ModelAttribute CommentDTO comment) {
//        commentFeignClient.createComment(taskId, comment);
//        return "redirect:/projects/tasks/" + taskId;
//    }
//
//
//
//    // 코멘트 삭제
//    @DeleteMapping("/{commentId}")
//    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
//        commentFeignClient.deleteComment(commentId);
//        return ResponseEntity.noContent().build();
//    }
//}