package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.dto.MilestoneDTO;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MileStoneController {

    @Autowired
    private TaskClient milestoneFeignClient;


    @GetMapping("/milestones")
    public String getMilestoneAddPage(){
        return "projectMilestoneAdd";
    }

    
    @PostMapping("/milestones")
    public String createMilestone( @ModelAttribute MilestoneDTO mileStone) {
        milestoneFeignClient.createMilestone(mileStone);
        return "redirect:/projects/"+mileStone.getProjectId();
    }
//
//    @PutMapping("/milestones/{milestoneId}")
//    public String updateMilestone(@PathVariable Long milestoneId, @ModelAttribute MileStone milestone){
//        mileStoneService.updateMilestone(milestoneId, milestone);
//        return "redirect:/tasks";
//    }
//
//    @DeleteMapping("/milestones/{milestoneId}")
//    public String deleteMilestone(@PathVariable Long milestoneId) {
//        mileStoneService.deleteMilestone(milestoneId);
//        return "redirect:/tasks";
//    }
}