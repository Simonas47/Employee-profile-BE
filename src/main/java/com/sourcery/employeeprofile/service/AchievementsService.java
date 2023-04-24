package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.*;
import com.sourcery.employeeprofile.model.Achievement;
import com.sourcery.employeeprofile.repository.AchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        Map<String, List<DropdownAchievementDto>> categoriesAndAchievementsMap = new TreeMap<>();
        List<Achievement> achievementBottomCategories = achievementsRepository.getBottomCategories();
        achievementBottomCategories.forEach(achievementSubcategory -> {
            String category = getTopCategoryName(achievementSubcategory);
            List<Achievement> achievements = achievementsRepository.getBottomAchievements(
                    achievementSubcategory.getId()
            );
            List<DropdownAchievementDto> dropdownAchievements = new ArrayList<>();
            achievements.forEach(achievement -> dropdownAchievements.add(new DropdownAchievementDto(
                    achievement.getId(),
                    achievement.getAchievementName()
            )));
            if (categoriesAndAchievementsMap.containsKey(category)) {
                dropdownAchievements.addAll(categoriesAndAchievementsMap.get(category));
                categoriesAndAchievementsMap.put(category, sortAchievementsList(dropdownAchievements));
            } else {
                categoriesAndAchievementsMap.put(category, sortAchievementsList(dropdownAchievements));
            }
        });
        List<SearchAchievementDto> categoriesAndAchievements = new ArrayList<>();
        categoriesAndAchievementsMap.forEach((category, achievements) -> {
            categoriesAndAchievements.add(new SearchAchievementDto(category, achievements));
        });
        return categoriesAndAchievements;
    }

    private String getTopCategoryName(Achievement achievementCategory) {
        String category = achievementCategory.getAchievementName();
        while (achievementCategory.getParentId() != null) {
            achievementCategory = achievementsRepository.getTopCategory(achievementCategory.getParentId());
            category = achievementCategory.getAchievementName();
        }
        return category;
    }

    private List<DropdownAchievementDto> sortAchievementsList(List<DropdownAchievementDto> unsortedAchievements) {
        return unsortedAchievements
                .stream()
                .sorted(Comparator.comparing(DropdownAchievementDto::getAchievementName))
                .collect(Collectors.toList());
    }
}
