package com.nhnacademy.minidorray_gateway.domain.project.controller;

import com.nhnacademy.minidorray_gateway.domain.project.dto.MilestoneDTO;
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

public class MileStoneControllerTest {

    @InjectMocks
    private MileStoneController mileStoneController;

    @Mock
    private TaskClient milestoneClient;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMilestoneAddPage() {
        // Given
        Long projectId = 1L;

        // When
        String viewName = mileStoneController.getMilestoneAddPage(projectId, model);

        // Then
        assertEquals("projectMilestoneAdd", viewName);
        verify(model, times(1)).addAttribute("projectId", projectId);
    }

    @Test
    public void testCreateMilestone() {
        // Given
        Long projectId = 1L;
        MilestoneDTO milestoneDto = new MilestoneDTO();

        // When
        String viewName = mileStoneController.createMilestone(projectId, milestoneDto, model);

        // Then
        assertEquals("redirect:/projects/project/" + milestoneDto.getProjectId(), viewName);
        verify(milestoneClient, times(1)).createMilestone(milestoneDto);
        verify(model, times(1)).addAttribute("projectId", projectId);
    }
}
