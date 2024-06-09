package com.nhnacademy.minidorray_gateway.domain.project.controller;

import com.nhnacademy.minidorray_gateway.domain.project.dto.TagDTO;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TagControllerTest {

    @InjectMocks
    private TagController tagController;

    @Mock
    private TaskClient taskClient;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTagsAddPage() {
        // Given
        Long projectId = 1L;

        // When
        String viewName = tagController.getTagsAddPage(projectId, model);

        // Then
        assertEquals("projectTagAdd", viewName);
        verify(model, times(1)).addAttribute("projectId", projectId);
    }




    @Test
    public void testUpdateTag() {
        // Given
        Long tagId = 1L;
        TagDTO tagDTO = new TagDTO();
        tagDTO.setProjectId(1L);
        when(taskClient.updateTag(tagId, tagDTO)).thenReturn(tagDTO);

        // When
        String viewName = tagController.updateTag(tagId, tagDTO);

        // Then
        assertEquals("redirect:/projects/project/" + tagDTO.getProjectId(), viewName);
        verify(taskClient, times(1)).updateTag(tagId, tagDTO);
    }

}
