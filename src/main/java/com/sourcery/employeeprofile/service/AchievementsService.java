package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.AchievementDto;
import com.sourcery.employeeprofile.dto.ChangedAchievementsDto;
import com.sourcery.employeeprofile.dto.EmployeeAchievementDto;
import com.sourcery.employeeprofile.dto.SearchAchievementDto;
import com.sourcery.employeeprofile.model.Achievement;
import com.sourcery.employeeprofile.repository.AchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.sourcery.employeeprofile.mapper.AchievementMapper.mapModelsToDtos;

@Service
public class AchievementsService {
    @Autowired
    AchievementsRepository achievementsRepository;

    public List<AchievementDto> getAllByEmployeeId(Integer employeeId) {
        return mapModelsToDtos(
                achievementsRepository.getAll(),
                achievementsRepository.getAchievementsByEmployeeId(employeeId)
        );
    }

    public void updateEmployeeAchievements(ChangedAchievementsDto changedAchievements) {
        for (EmployeeAchievementDto employeeAchievement : changedAchievements.getChangedAchievements()) {
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

    public List<SearchAchievementDto> getAchievementsCategories() {
        List<SearchAchievementDto> categoriesAndAchievements = new ArrayList<>();
        List<Achievement> achievementBottomCategories = achievementsRepository.getBottomCategories();
        achievementBottomCategories.forEach(achievementSubcategory -> {
            String category = getTopCategoryName(achievementSubcategory);
            List<Achievement> achievements = achievementsRepository
                    .getBottomAchievements(achievementSubcategory.getId());
            achievements.forEach(achievement -> {
                categoriesAndAchievements.add(new SearchAchievementDto(
                        category,
                        achievement.getId(),
                        achievement.getAchievementName()
                ));
            });
        });
        return categoriesAndAchievements
                .stream()
                .sorted(Comparator
                        .comparing(SearchAchievementDto::getCategory)
                        .thenComparing(SearchAchievementDto::getAchievementName)
                )
                .collect(Collectors.toList());
    }

    private String getTopCategoryName(Achievement achievementCategory) {
        String category = achievementCategory.getAchievementName();
        while (achievementCategory.getParentId() != null) {
            achievementCategory = achievementsRepository.getTopCategory(achievementCategory.getParentId());
            category = achievementCategory.getAchievementName();
        }
        return category;
    }
}
