//package com.nhnacademy.minidorray_gateway.domain.project.controller;
//
//
//import com.nhnacademy.minidorray_gateway.domain.project.dto.MilestoneDTO;
//import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class MileStoneController {
//
//    @Autowired
//    private TaskClient milestoneFeignClient;
//
//
//    @GetMapping("/milestones")
//    public String getMilestoneAddPage(){
//        return "projectMilestoneAdd";
//    }
//
//
//    @PostMapping("/milestones/projectId")
//    public String createMilestone(@PathVariable("projectId") Long projectId, @ModelAttribute MilestoneDTO milestone) {
//        milestoneFeignClient.createMilestone(milestone);
//        return "redirect:/projects/"+milestone.getProjectId();
//    }
////    // 마일스톤 선택
////    @PostMapping("/milestones/{milestoneId}/tasks/{taskId}")
////    public ResponseEntity<MilestoneDTO> setMilestoneToTask(@PathVariable("milestoneId") Long milestoneId, @PathVariable("taskId") Long taskId) {
////        Milestone milestone = milestoneService.setMileStoneToTask(taskId, milestoneId);
////        MilestoneDTO resp = MilestoneDTO.builder()
////                .milestoneId(milestone.getId())
////                .projectId(milestone.getProject().getId())
////                .milestoneName(milestone.getName())
////                .startDate(milestone.getStartDate())
////                .dueDate(milestone.getDueDate())
////                .taskId(milestone.getTask().getId())
////                .build();
////        return ResponseEntity.ok(resp);
////    }
////
////    // 마일스톤 삭제
////    @DeleteMapping("/milestones/{milestoneId}/tasks/{taskId}")
////    public ResponseEntity<Void> deleteMilestoneFromTask(@PathVariable("milestoneId") Long milestoneId, @PathVariable("taskId") Long taskId) {
////        milestoneService.deleteMilestoneFromTask(milestoneId, taskId);
////        return ResponseEntity.noContent().build();
////    }
////
////    // 마일스톤 목록 가져오기
////    @GetMapping("/project-milestones/{projectId}")
////    public ResponseEntity<List<MilestoneDTO>> getMilestones(@PathVariable("projectId") Long projectId) {
////        List<MilestoneDTO> resp = milestoneService.getMilestoneByProjectId(projectId).stream()
////                .map(milestone -> MilestoneDTO.builder()
////                        .milestoneId(milestone.getId())
////                        .projectId(milestone.getProject().getId())
////                        .milestoneName(milestone.getName())
////                        .startDate(milestone.getStartDate())
////                        .dueDate(milestone.getDueDate())
////                        .taskId(Optional.ofNullable(milestone.getTask()).map(Task::getId).orElse(null))
////                        .build())
////                .toList();
////        return ResponseEntity.ok(resp);
////    }
//
//}