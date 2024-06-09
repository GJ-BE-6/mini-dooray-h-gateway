package com.nhnacademy.minidorray_gateway.domain.project.controller;

import com.nhnacademy.minidorray_gateway.domain.project.dto.TaskDto;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskClient taskClient;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTasks() {
        // Given
        Long taskId = 1L;
        when(taskClient.getTask(taskId)).thenReturn(new TaskDto());
        when(taskClient.getComments(taskId)).thenReturn(new ArrayList<>());
        when(taskClient.getMilestoneByTaskId(taskId)).thenReturn(new ArrayList<>());
        when(taskClient.getTagByProjectId(anyLong())).thenReturn(new ArrayList<>());
        when(taskClient.getTaskTagsByTaskId(taskId)).thenReturn(new ArrayList<>());

        // When
        String viewName = taskController.getTasks(taskId, model);

        // Then
        assertEquals("taskView", viewName);
        verify(model, times(1)).addAttribute(eq("task"), any(TaskDto.class));
        verify(model, times(1)).addAttribute(eq("comments"), anyList());
        verify(model, times(1)).addAttribute(eq("milestones"), anyList());
        verify(model, times(1)).addAttribute(eq("tags"), anyList());
        verify(model, times(1)).addAttribute(eq("settingTags"), anyList());
    }

    @Test
    public void testGetAddTasks() {
        // Given
        Long projectId = 1L;

        // When
        String viewName = taskController.getAddTasks(projectId, model);

        // Then
        assertEquals("projectTaskAdd", viewName);
        verify(model, times(1)).addAttribute("projectId", projectId);
    }



    @Test
    public void testEditTaskForm() {
        // Given
        Long taskId = 1L;
        when(taskClient.getTask(taskId)).thenReturn(new TaskDto());

        // When
        String viewName = taskController.editTaskForm(taskId, model);

        // Then
        assertEquals("taskEdit", viewName);
        verify(model, times(1)).addAttribute(eq("task"), any(TaskDto.class));
    }


    @Test
    public void testDeleteTask() {
        // Given
        Long taskId = 1L;
        TaskDto taskDto = new TaskDto();
        when(taskClient.getTask(taskId)).thenReturn(taskDto);

        // When
        String viewName = taskController.deleteTask(taskId);

        // Then
        assertEquals("redirect:/projects/project/" + taskDto.getProjectId(), viewName);
        verify(taskClient, times(1)).deleteTask(taskId);
    }

    // Stub for SecurityContextHolder.getContext().getAuthentication()
    private static class SecurityContextStub extends SecurityContextHolder {
        private final Authentication authentication;

        public SecurityContextStub(Authentication authentication) {
            this.authentication = authentication;
        }


    }
}
