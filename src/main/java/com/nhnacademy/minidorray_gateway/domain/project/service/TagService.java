package com.nhnacademy.minidorray_gateway.domain.project.service;

import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import com.nhnacademy.minidorray_gateway.domain.project.model.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private final TaskClient taskClient;

    public TagService(TaskClient taskClient) {
        this.taskClient = taskClient;
    }

    public void createTag(Tag tag) {
        taskClient.createTag(tag);
    }

    public void updateTag(Long tagId, Tag tag) {
        taskClient.updateTag(tagId, tag);
    }

    public void deleteTag(Long tagId) {
        taskClient.deleteTag(tagId);
    }
}
