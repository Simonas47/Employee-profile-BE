package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.AchievementDto;
import com.sourcery.employeeprofile.dto.ChangedAchievementsDto;
import com.sourcery.employeeprofile.service.AchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/achievements")
public class AchievementController {
    @Autowired
    AchievementsService achievementsService;

    @GetMapping("/getAllByEmployeeId/{employeeId}")
    public List<AchievementDto> getAllByEmployeeId(@PathVariable Integer employeeId) {
        return achievementsService.getAllByEmployeeId(employeeId);
    }

    @PutMapping("/update")
    public void updateEmployeeAchievement(@RequestBody ChangedAchievementsDto changedAchievements) {
        achievementsService.updateEmployeeAchievements(changedAchievements);
    }
}
