package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.EmployeeAchievementDto;
import com.sourcery.employeeprofile.dto.AchievementDto;
import com.sourcery.employeeprofile.dto.ChangedAchievementsDto;
import com.sourcery.employeeprofile.repository.AchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sourcery.employeeprofile.mapper.AchievementMapper.mapModelsToDtos;

@Service
public class AchievementsService {
    @Autowired
    AchievementsRepository achievementsRepository;

    public List<AchievementDto> getAllByEmployeeId(Integer employeeId) {
        return mapModelsToDtos(achievementsRepository.getAll(), achievementsRepository.getAchievementsByEmployeeId(employeeId));
    }

    public void updateEmployeeAchievements(ChangedAchievementsDto changedAchievements) {
        for (EmployeeAchievementDto employeeAchievement : changedAchievements.getChangedAchievements())  {
            achievementsRepository.deleteAchievementEmployeeRelationshipById(
                    employeeAchievement.getEmployeeId(),
                    employeeAchievement.getAchievementId()
            );
            if (employeeAchievement.isChecked()) {
                achievementsRepository.createNewAchievementEmployeeRelationship(
                        employeeAchievement.getAchievementId(),
                        employeeAchievement.getIssueDate(),
                        employeeAchievement.getExpiringDate(),
                        employeeAchievement.getEmployeeId()
                );
            }
        }
    }

}
