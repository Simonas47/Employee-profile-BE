package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AchievementDto {
    private Integer achievementId;
    private String achievementName;
    private Boolean checked;
    private Date issueDate;
    private Date expiringDate;
    private boolean subItemsAreAchievements;
    private Integer indent;
    private Integer parentAchievementId;
    private boolean isCategory;
}
