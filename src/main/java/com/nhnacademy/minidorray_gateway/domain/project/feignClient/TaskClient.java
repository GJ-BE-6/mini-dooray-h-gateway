package com.nhnacademy.minidorray_gateway.domain.project.feignClient;

import com.nhnacademy.minidorray_gateway.domain.project.dto.*;
import com.nhnacademy.minidorray_gateway.domain.project.model.Project;
import com.nhnacademy.minidorray_gateway.domain.project.model.ProjectMember;
import com.nhnacademy.minidorray_gateway.domain.project.model.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "task-api", url = "http://localhost:8082")
public interface TaskClient {


    // 모든 프로젝트 가져오기
    @GetMapping("/projects")
    List<Project> getProjects();

    // User가 속한 프로젝트들만 가져오기
    @GetMapping("/projects/users/{id}")
    List<ProjectDto> getAllProjectsByUserId(@PathVariable String id);

    // ProjectId에 해당하는 프로젝트 가져오기
    @GetMapping("/projects/{id}")
    ProjectDto getProjectById(@PathVariable("id") Long id);

    // 프로젝트 생성 (생성자가 자동으로 관리자로 지정)
    @PostMapping("/projects/users/{id}")
    Project createProject(@PathVariable String id, @RequestBody Project project);

// 프로젝트 수정
    @PutMapping("/projects")
    void updateProject(@RequestBody Project project);

// 프로젝트 삭제
    @DeleteMapping("/projects/{id}")
    void deleteProject(@PathVariable Long id);


// ProjectMember Controller

    // 해당 프로젝트에 멤버 추가
    @PostMapping("/projects/{projectId}/members")
    ProjectMember addMember(@PathVariable("projectId") Long projectId, @RequestBody MemberDto member);    // RequestBody 멤버 객체 변수 다름




    // 해당 프로젝트 멤버 조회
    @GetMapping("/projects/{id}/members")
    List<ProjectMemberDto> getMembers(@PathVariable Long id);

    // 해당 프로젝트에서 특정 멤버 삭제
    @DeleteMapping("/projects/{projectId}/members/{memberId}")
    void deleteMember(@PathVariable Long projectId, @PathVariable String memberId);


// Task Controller

    // ProjectId에 해당하는 Task 목록 조회
    @GetMapping("/projects/{id}/tasks")
    List<TaskDto> getTasks(@PathVariable Long id);

    // 특정 Task 조회
    @GetMapping("/projects/tasks/{id}")
    TaskDto getTask(@PathVariable Long id);

    // Task 등록
    @PostMapping("/projects/tasks")
    Task createTask(@RequestBody TaskCreateDto task);

    // Task 수정
    @PutMapping("/projects/tasks")
    void updateTask(@RequestBody TaskDto task);

    // Task 삭제
    @DeleteMapping("/projects/tasks/{id}")
    void deleteTask(@PathVariable Long id);


    //  Task 에 달린 Comment List로 가져오기
    @GetMapping("/tasks/{taskId}/comments")
    public List<CommentResponseDTO> getComments(@PathVariable Long taskId);

    @PostMapping("/tasks/{taskId}/comments")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable("taskId") Long taskId, @RequestBody CommentDTO commentDTO);

    // MilestoneId 로 Milestone 가져오기
    @GetMapping("/milestones/{milestoneId}")
    public ResponseEntity<MilestoneDTO> getMilestone(@PathVariable Long milestoneId);

    // Task에 Milestone 등록
    @PostMapping("/milestones/{milestoneId}/tasks/{taskId}")
    MilestoneDTO setMilestoneToTask(@PathVariable("milestoneId") Long milestoneId, @PathVariable("taskId") Long taskId);

    // Task에서 Milestone 삭제
    @DeleteMapping("/tasks/milestones/{milestoneId}")
    public ResponseEntity<Void> deleteMilestoneFromTask(@PathVariable("milestoneId") Long milestoneId);

    // Project에 등록된 Milestone List로 가져오기
    @GetMapping("/projects/{projectId}/milestones")
    public List<MilestoneDTO> getMilestones(@PathVariable("projectId") Long projectId);

    // TaskId로 Task에 등록된 Milestone 가져오기
    @GetMapping("/tasks/{taskId}/milestone")
    public List<MilestoneDTO> getMilestoneByTaskId(@PathVariable("taskId") Long taskId);


    // ProjectId로 프로젝트에 등록된 Tag List 가져오기
    @GetMapping("/projects/{projectId}/tags")
    List<TagResponseDTO> getTagByProjectId(@PathVariable("projectId") Long projectId);

    // Task에 Tag 등록
    @PostMapping("/tasks/tag")
    public void setTagToTask(@RequestBody TaskTagDTO taskTagDTO);

    // Task에서 Tag 제거
    @DeleteMapping("/tasks/tag")
    public void deleteTagFromTask(@RequestBody TaskTagDTO taskTagDTO);

    // TaskId로 Task에 등록된 Tag List로 가져오기
    @GetMapping("/tasks/{taskId}/tags")
    public List<TagResponseDTO> getTaskTagsByTaskId(@PathVariable("taskId") Long taskId);

    @PostMapping("/milestones")
    public MilestoneDTO createMilestone(@RequestBody MilestoneDTO milestoneDTO);


    @PostMapping("/tags")
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tagDTO);

    @DeleteMapping("/tags/{tagId}")
    public void deleteTag(@PathVariable Long tagId);

    @PutMapping("/tags/{tagId}")
    public TagDTO updateTag(@PathVariable("tagId") Long tagId, @RequestBody TagDTO tagDTO);

    @GetMapping("/tags/{tagId}")
    public TagResponseDTO getTagById(@PathVariable Long tagId);


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId);
}