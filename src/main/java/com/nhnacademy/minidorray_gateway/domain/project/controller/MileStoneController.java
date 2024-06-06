package com.nhnacademy.minidorray_gateway.domain.project.controller;


import com.nhnacademy.minidorray_gateway.domain.project.model.MileStone;
import com.nhnacademy.minidorray_gateway.domain.project.service.MileStoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MileStoneController {

    @Autowired
    private MileStoneService mileStoneService;

    @PostMapping("/milestones")
    public String createMilestone( @ModelAttribute MileStone mileStone) {
        mileStoneService.createMilestone(mileStone);
        return "redirect:/tasks/";
    }

    @PutMapping("/milestones/{milestoneId}")
    public String updateMilestone(@PathVariable Long milestoneId, @ModelAttribute MileStone milestone){
        mileStoneService.updateMilestone(milestoneId, milestone);
        return "redirect:/tasks";
    }

    @DeleteMapping("/milestones/{milestoneId}")
    public String deleteMilestone(@PathVariable Long milestoneId) {
        mileStoneService.deleteMilestone(milestoneId);
        return "redirect:/tasks";
    }
}