package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AchievementDto {
    private UUID id;
    private String achievementName;
    private Boolean checked;
    private Date issueDate;
    private Date expiringDate;
    private boolean subItemsAreAchievements;
    private Integer indent;
    private UUID parentId;
}
