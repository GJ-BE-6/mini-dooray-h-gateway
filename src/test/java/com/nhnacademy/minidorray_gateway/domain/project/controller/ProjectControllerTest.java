package com.nhnacademy.minidorray_gateway.domain.project.controller;

import com.nhnacademy.minidorray_gateway.domain.project.dto.ProjectDto;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import com.nhnacademy.minidorray_gateway.domain.project.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProjectControllerTest {

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private TaskClient taskClient;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMemberAddPage() {
        // When
        String viewName = projectController.getMemberAddPage();

        // Then
        assertEquals("projectMemberAdd", viewName);
    }

    @Test
    public void testGetProjectAddPage() {
        // Given
        String userId = "user123";

        // When
        String viewName = projectController.getProjectAddPage(userId, model);

        // Then
        assertEquals("projectAdd", viewName);
        verify(model, times(1)).addAttribute("userId", userId);
    }

    @Test
    public void testCreateProject() {
        // Given
        String userId = "user123";
        Project projectDto = new Project(1L, "Project 1", "Active");

        // When
        String viewName = projectController.createProject(userId, model, projectDto);

        // Then
        assertEquals("redirect:/projects/" + userId, viewName);
        verify(model, times(1)).addAttribute("userId", userId);
        verify(taskClient, times(1)).createProject(userId, projectDto);
    }

    @Test
    public void testGetProjects() {
        // Given
        String userId = "user123";
        List<ProjectDto> projects = new ArrayList<>();
        projects.add(new ProjectDto(1L, "Project 1", "Active"));
        projects.add(new ProjectDto(2L, "Project 2", "Dormant"));
        when(taskClient.getAllProjectsByUserId(userId)).thenReturn(projects);

        // When
        String viewName = projectController.getProjects(userId, model);

        // Then
        assertEquals("projectMain", viewName);
        verify(taskClient, times(1)).getAllProjectsByUserId(userId);
        verify(model, times(1)).addAttribute("projects", projects);
        verify(model, times(1)).addAttribute("userId", userId);
    }


}
