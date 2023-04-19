package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.SkillDto;
import com.sourcery.employeeprofile.dto.ChangedSkillsDto;
import com.sourcery.employeeprofile.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/skills")
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {
    @Autowired
    SkillsService skillsService;

    @GetMapping("/getAllByEmployeeId/{employeeId}")
    public List<SkillDto> getAllByEmployeeId(@PathVariable UUID employeeId) {
        return skillsService.getAllByEmployeeId(employeeId);
    }

    @PutMapping("/newUpdate")
    public void updateEmployeeSkill(@RequestBody ChangedSkillsDto request) {
        skillsService.updateEmployeeSkill(request);
        System.out.println(request);
    }
}
