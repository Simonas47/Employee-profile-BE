package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.AchievementDto;
import com.sourcery.employeeprofile.dto.ChangedAchievementsDto;
import com.sourcery.employeeprofile.service.AchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/achievements")
@CrossOrigin(origins = {"http://localhost:3000", "https://employee-profile.devbstaging.com"})
public class AchievementController {
    @Autowired
    AchievementsService achievementsService;

    @GetMapping("/getAllByEmployeeId/{employeeId}")
    public List<AchievementDto> getAllByEmployeeId(@PathVariable UUID employeeId) {
        return achievementsService.getAllByEmployeeId(employeeId);
    }

    @PutMapping("/update")
    public void updateEmployeeAchievement(@RequestBody ChangedAchievementsDto changedAchievements) {
        achievementsService.updateEmployeeAchievements(changedAchievements);
    }
}
