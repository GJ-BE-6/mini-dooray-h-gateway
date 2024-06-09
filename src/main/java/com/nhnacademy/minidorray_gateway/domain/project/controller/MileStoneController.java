package com.nhnacademy.minidorray_gateway.domain.project.controller;

import com.nhnacademy.minidorray_gateway.domain.project.dto.MilestoneDTO;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MileStoneController {

    private final TaskClient milestoneFeignClient;

    public MileStoneController(TaskClient milestoneFeignClient) {
        this.milestoneFeignClient = milestoneFeignClient;
    }

    @Operation(summary = "마일스톤 추가 페이지로 이동", description = "마일스톤 추가페이지로 이동합니다")
    @GetMapping("/milestones/{projectId}")
    public String getMilestoneAddPage(@PathVariable("projectId") Long projectId, Model model){
        model.addAttribute("projectId", projectId);
        return "projectMilestoneAdd";
    }

    @Operation(summary = "마일스톤 생성", description = "마일스톤 생성 기능을 수행합니다")
    @PostMapping("/milestones/{projectId}")
    public String createMilestone(@PathVariable("projectId") Long projectId, @ModelAttribute MilestoneDTO milestone, Model model) {
        milestoneFeignClient.createMilestone(milestone);
        model.addAttribute("projectId", projectId);
        return "redirect:/projects/project/" + milestone.getProjectId();
    }


//    // 마일스톤 선택
//    @PostMapping("/milestones/{milestoneId}/tasks/{taskId}")
//    public ResponseEntity<MilestoneDTO> setMilestoneToTask(@PathVariable("milestoneId") Long milestoneId, @PathVariable("taskId") Long taskId) {
//        Milestone milestone = milestoneService.setMileStoneToTask(taskId, milestoneId);
//        MilestoneDTO resp = MilestoneDTO.builder()
//                .milestoneId(milestone.getId())
//                .projectId(milestone.getProject().getId())
//                .milestoneName(milestone.getName())
//                .startDate(milestone.getStartDate())
//                .dueDate(milestone.getDueDate())
//                .taskId(milestone.getTask().getId())
//                .build();
//        return ResponseEntity.ok(resp);
//    }
//
//    // 마일스톤 삭제
//    @DeleteMapping("/milestones/{milestoneId}/tasks/{taskId}")
//    public ResponseEntity<Void> deleteMilestoneFromTask(@PathVariable("milestoneId") Long milestoneId, @PathVariable("taskId") Long taskId) {
//        milestoneService.deleteMilestoneFromTask(milestoneId, taskId);
//        return ResponseEntity.noContent().build();
//    }
//
//    // 마일스톤 목록 가져오기
//    @GetMapping("/project-milestones/{projectId}")
//    public ResponseEntity<List<MilestoneDTO>> getMilestones(@PathVariable("projectId") Long projectId) {
//        List<MilestoneDTO> resp = milestoneService.getMilestoneByProjectId(projectId).stream()
//                .map(milestone -> MilestoneDTO.builder()
//                        .milestoneId(milestone.getId())
//                        .projectId(milestone.getProject().getId())
//                        .milestoneName(milestone.getName())
//                        .startDate(milestone.getStartDate())
//                        .dueDate(milestone.getDueDate())
//                        .taskId(Optional.ofNullable(milestone.getTask()).map(Task::getId).orElse(null))
//                        .build())
//                .toList();
//        return ResponseEntity.ok(resp);
//    }

}