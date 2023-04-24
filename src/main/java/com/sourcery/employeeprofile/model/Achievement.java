package com.sourcery.employeeprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Achievement {
    private Integer id;
    private String achievementName;
    private Integer parentId;
    private boolean subItemsAreAchievements;
    private String uniqueAchievementIdentifier;
}
