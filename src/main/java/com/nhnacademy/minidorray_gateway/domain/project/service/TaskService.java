package com.nhnacademy.minidorray_gateway.domain.project.service;

import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import com.nhnacademy.minidorray_gateway.domain.project.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {
    private final TaskClient taskClient;

    public TaskService(TaskClient taskClient) {
        this.taskClient = taskClient;
    }

    public List<Task> getTasks() {
        return taskClient.getTasks();
    }

    public void createTask(Task task) {
        taskClient.createTask(task);
    }

    public void updateTask(Long taskId, Task task) {
        taskClient.updateTask(taskId, task);
    }

    public void deleteTask(Long taskId) {
        taskClient.deleteTask(taskId);
    }
}
