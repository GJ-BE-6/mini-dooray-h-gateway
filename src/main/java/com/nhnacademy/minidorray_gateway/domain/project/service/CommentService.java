package com.nhnacademy.minidorray_gateway.domain.project.service;

import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import com.nhnacademy.minidorray_gateway.domain.project.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final TaskClient taskClient;

    public CommentService(TaskClient taskClient) {
        this.taskClient = taskClient;
    }

    public void createComment(Long taskId, Comment comment) {
        taskClient.createComment(taskId, comment);
    }

    public void updateComment(Long commentId, Comment comment) {
        taskClient.updateComment(commentId, comment);
    }

    public void deleteComment(Long commentId) {
        taskClient.deleteComment(commentId);
    }
}
