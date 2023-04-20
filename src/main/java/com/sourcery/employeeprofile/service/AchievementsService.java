package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.AchievementDto;
import com.sourcery.employeeprofile.model.AchievementEmployee;
import com.sourcery.employeeprofile.repository.AchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.sourcery.employeeprofile.mapper.AchievementMapper.mapModelsToDtos;

@Service
public class AchievementsService {

    @Autowired
    AchievementsRepository achievementsRepository;

    public List<AchievementDto> getAllByEmployeeId(UUID employeeId) {
        return mapModelsToDtos(achievementsRepository.getAll(), achievementsRepository.getAchievementsByEmployeeId(employeeId));
    }

    public void updateEmployeeAchievement(UUID achievementId, UUID employeeId, boolean checked, Date issueDate, Date expiringDate) {
        if (checked) {
            achievementsRepository.deleteAchievementEmployeeRelationshipById(
                    employeeId,
                    achievementId
            );
            achievementsRepository.createNewAchievementEmployeeRelationship(
                    achievementId,
                    issueDate,
                    expiringDate,
                    employeeId
            );
        } else {
            achievementsRepository.deleteAchievementEmployeeRelationshipById(
                    employeeId,
                    achievementId
            );
        }
    }
}
