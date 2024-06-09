package com.nhnacademy.minidorray_gateway.domain.project.controller;

import com.nhnacademy.minidorray_gateway.domain.project.dto.MemberDto;
import com.nhnacademy.minidorray_gateway.domain.project.feignClient.TaskClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/projects")
public class ProjectMemberController {

    private final TaskClient projectFeignClient;

    @Operation(summary = "멤버 추가페이지로 이동", description = "멤버 추가페이지로 이동합니다")
    @GetMapping("/{projectId}/members")
    public String members(@PathVariable("projectId") Long projectId, Model model) {
        model.addAttribute("projectId", projectId);

        return "projectMemberAdd";
    }

    @Operation(summary = "멤버 추가기능", description = "프로젝트 멤버를 추가하는 기능을 수행합니다")
    @PostMapping("/{projectId}/members")
    public String addMember(@PathVariable("projectId") Long projectId,
                            @RequestParam(name = "memberId") String memberId,
                            Model model) {

        MemberDto memberDto = new MemberDto(memberId);

        model.addAttribute("projectId", projectId);
        model.addAttribute("member", memberDto);

        projectFeignClient.addMember(projectId, memberDto);

        return "redirect:/projects/project/" + projectId;
    }


    @Operation(summary = "프로젝트 멤버 삭제", description = "프로젝트 멤버를 삭제하는 기능을 수행합니다")
    @GetMapping("/{projectId}/members/{memberId}")
    public String deleteMember(@PathVariable("projectId") Long projectId,
                               @PathVariable("memberId") String memberId,
                               Model model) {

        projectFeignClient.deleteMember(projectId, memberId);

        model.addAttribute("projectId", projectId);

        return "redirect:/projects/project/" + projectId;
    }
}
