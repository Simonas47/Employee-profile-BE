package com.sourcery.employeeprofile.mapper;

import com.sourcery.employeeprofile.dto.AchievementDto;
import com.sourcery.employeeprofile.model.Achievement;
import com.sourcery.employeeprofile.model.AchievementEmployee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AchievementMapper {
    public static List<AchievementDto> mapModelsToDtos(List<Achievement> achievementModelList,
                                                       List<AchievementEmployee> achievementEmployeeList) {
        Integer counter = 0;
        List<AchievementDto> outputList = new ArrayList<>();
        for (Achievement achievement : achievementModelList) {
            AchievementDto achievementDto = new
                    AchievementDto(achievement.getId(),
                    achievement.getAchievementName(),
                    isChecked(achievement, achievementEmployeeList),
                    getIssueDate(achievement, achievementEmployeeList),
                    getExpiringDate(achievement, achievementEmployeeList),
                    achievement.isSubItemsAreAchievements(),
                    getIndent(achievement, counter, achievementModelList),
                    achievement.getParentId());
            outputList.add(achievementDto);
        }
        return outputList;
    }

    private static Boolean isChecked(Achievement achievement, List<AchievementEmployee> achievementEmployeeList) {
        return getAchievementEmployee(achievement, achievementEmployeeList) != null;
    }

    private static Date getIssueDate(Achievement achievement, List<AchievementEmployee> achievementEmployeeList) {
        AchievementEmployee achievementEmployee = getAchievementEmployee(achievement, achievementEmployeeList);
        if (achievementEmployee != null) return achievementEmployee.getIssueDate();
        return null;
    }
    private static Date getExpiringDate(Achievement achievement, List<AchievementEmployee> achievementEmployeeList) {
        AchievementEmployee achievementEmployee = getAchievementEmployee(achievement, achievementEmployeeList);
        if (achievementEmployee != null) return achievementEmployee.getExpiringDate();
        return null;
    }

    private static AchievementEmployee getAchievementEmployee(Achievement achievement, List<AchievementEmployee> achievementEmployeeList) {
        for (AchievementEmployee achievementEmployee: achievementEmployeeList) {
            if (achievementEmployee.getAchievementId().equals(achievement.getId())) {
                return achievementEmployee;
            }
        }
        return null;
    }

    private static Integer getIndent(Achievement achievement, Integer counter, List<Achievement> achievementModelList) {
        for (Achievement achievementModel : achievementModelList) {
            if (achievement.getParentId() == null) {
                return counter;
            } else if (achievementModel.getId().equals(achievement.getParentId())) {
                counter++;
                return getIndent(achievementModel, counter, achievementModelList);
            }
        }
        throw new RuntimeException("no values in DB!");
    }

}
