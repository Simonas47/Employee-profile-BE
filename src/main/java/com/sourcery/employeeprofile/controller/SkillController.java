package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.EmployeeSkillDto;
import com.sourcery.employeeprofile.dto.SearchSkillDto;
import com.sourcery.employeeprofile.dto.SkillDto;
import com.sourcery.employeeprofile.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/skills")
@CrossOrigin(origins = {"http://localhost:3000", "https://employee-profile.devbstaging.com"})
public class SkillController {
    @Autowired
    SkillsService skillsService;

    @GetMapping("/getAllByEmployeeId/{employeeId}")
    public List<SkillDto> getAllByEmployeeId(@PathVariable UUID employeeId) {
        return skillsService.getAllByEmployeeId(employeeId);
    }

    @PutMapping("/update")
    public void updateEmployeeSkill(@RequestBody EmployeeSkillDto employeeSkillDto) {
        skillsService.updateEmployeeSkill(
                employeeSkillDto.getSkillId(),
                employeeSkillDto.getEmployeeId(),
                employeeSkillDto.isChecked(),
                employeeSkillDto.getSkillLevel()
        );
    }

    @GetMapping("/getSkillsCategories")
    public List<SearchSkillDto> getSkillsCategories() {
        return skillsService.getSkillsCategories();
    }
}
