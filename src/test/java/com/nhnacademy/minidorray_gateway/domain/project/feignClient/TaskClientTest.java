package com.nhnacademy.minidorray_gateway.domain.project.feignClient;

import com.nhnacademy.minidorray_gateway.domain.project.dto.*;
import com.nhnacademy.minidorray_gateway.domain.project.model.Project;
import com.nhnacademy.minidorray_gateway.domain.project.model.ProjectMember;
import com.nhnacademy.minidorray_gateway.domain.project.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskClientTest {

    @Mock
    private TaskClient taskClient;

    @Test
    public void testGetProjects() {
        // Given
        List<Project> projects = new ArrayList<>();
        // Populate projects list with dummy data

        // When
        when(taskClient.getProjects()).thenReturn(projects);

        // Then
        List<Project> result = taskClient.getProjects();
        // Assertion for result
    }

    @Test
    public void testCreateProject() {
        // Given
        Project project = new Project();
        // Populate project with dummy data

        // When
        when(taskClient.createProject(anyString(), any(Project.class))).thenReturn(project);

        // Then
        Project result = taskClient.createProject("userId", project);
        // Assertion for result
    }

    @Test
    public void testGetTasks() {
        // Given
        Long projectId = 1L;
        List<TaskDto> tasks = new ArrayList<>();
        // Populate tasks list with dummy data

        // When
        when(taskClient.getTasks(projectId)).thenReturn(tasks);

        // Then
        List<TaskDto> result = taskClient.getTasks(projectId);
        // Assertion for result
    }

    @Test
    public void testCreateTask() {
        // Given
        TaskCreateDto taskCreateDto = new TaskCreateDto();
        // Populate taskCreateDto with dummy data

        // When
        Task task = new Task(); // Assuming Task is returned upon creation
        when(taskClient.createTask(taskCreateDto)).thenReturn(task);

        // Then
        Task result = taskClient.createTask(taskCreateDto);
        // Assertion for result
    }

    @Test
    public void testAddMember() {
        // Given
        Long projectId = 1L;
        MemberDto memberDto = new MemberDto("user");
        // Populate memberDto with dummy data

        // When
        ProjectMember projectMember = new ProjectMember(); // Assuming ProjectMember is returned upon addition
        when(taskClient.addMember(projectId, memberDto)).thenReturn(projectMember);

        // Then
        ProjectMember result = taskClient.addMember(projectId, memberDto);
        // Assertion for result
    }

    @Test
    public void testGetComments() {
        // Given
        Long taskId = 1L;
        List<CommentResponseDTO> comments = new ArrayList<>();
        // Populate comments list with dummy data

        // When
        when(taskClient.getComments(taskId)).thenReturn(comments);

        // Then
        List<CommentResponseDTO> result = taskClient.getComments(taskId);
        // Assertion for result
    }

    @Test
    public void testCreateComment() {
        // Given
        Long taskId = 1L;
        CommentDTO commentDTO = new CommentDTO();
        // Populate commentDTO with dummy data

        // When
        ResponseEntity<CommentResponseDTO> responseEntity = ResponseEntity.ok().build(); // Assuming successful creation
        when(taskClient.createComment(taskId, commentDTO)).thenReturn(responseEntity);

        // Then
        ResponseEntity<CommentResponseDTO> result = taskClient.createComment(taskId, commentDTO);
        // Assertion for result
    }

    @Test
    public void testGetMilestone() {
        // Given
        Long milestoneId = 1L;
        MilestoneDTO milestoneDTO = new MilestoneDTO();
        // Populate milestoneDTO with dummy data

        // When
        when(taskClient.getMilestone(milestoneId)).thenReturn(ResponseEntity.ok(milestoneDTO));

        // Then
        ResponseEntity<MilestoneDTO> result = taskClient.getMilestone(milestoneId);
        // Assertion for result
    }

    @Test
    public void testSetMilestoneToTask() {
        // Given
        Long milestoneId = 1L;
        Long taskId = 1L;

        // When
        MilestoneDTO milestoneDTO = new MilestoneDTO(); // Assuming MilestoneDTO is returned upon addition
        when(taskClient.setMilestoneToTask(milestoneId, taskId)).thenReturn(milestoneDTO);

        // Then
        MilestoneDTO result = taskClient.setMilestoneToTask(milestoneId, taskId);
        // Assertion for result
    }

    @Test
    public void testGetMilestones() {
        // Given
        Long projectId = 1L;
        List<MilestoneDTO> milestones = new ArrayList<>();
        // Populate milestones list with dummy data

        // When
        when(taskClient.getMilestones(projectId)).thenReturn(ResponseEntity.ok(milestones));

        // Then
        ResponseEntity<List<MilestoneDTO>> result = taskClient.getMilestones(projectId);
        // Assertion for result
    }

    // Add more test methods for other functionalities as needed
}
