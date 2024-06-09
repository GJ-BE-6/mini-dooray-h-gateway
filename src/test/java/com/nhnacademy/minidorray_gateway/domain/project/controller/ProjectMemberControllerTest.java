package com.nhnacademy.minidorray_gateway.domain.project.controller;

import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ProjectMemberControllerTest {

    @InjectMocks
    private ProjectMemberController projectMemberController;

    @Mock
    private TaskClient taskClient;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMembers() {
        // Given
        Long projectId = 1L;

        // When
        String viewName = projectMemberController.members(projectId, model);

        // Then
        assertEquals("projectMemberAdd", viewName);
        verify(model, times(1)).addAttribute("projectId", projectId);
    }


    @Test
    public void testDeleteMember() {
        // Given
        Long projectId = 1L;
        String memberId = "member123";

        // When
        String viewName = projectMemberController.deleteMember(projectId, memberId, model);

        // Then
        assertEquals("redirect:/projects/project/" + projectId, viewName);
        verify(taskClient, times(1)).deleteMember(projectId, memberId);
        verify(model, times(1)).addAttribute("projectId", projectId);
    }
}
