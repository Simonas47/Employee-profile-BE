package com.sourcery.employeeprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Achievement {
    private UUID id;
    private String achievementName;
    private UUID parentId;
    private boolean subItemsAreAchievements;
    private String uniqueAchievementIdentifier;
}
