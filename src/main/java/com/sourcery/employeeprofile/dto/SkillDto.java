package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SkillDto {
    private Integer skillId;
    private String skillName;
    private Boolean checked;
    private String skillLevel;
    private boolean subItemsAreSkills;
    private Integer indent;
    private Integer parentSkillId;
    private boolean isLanguage;
    private boolean isCategory;
}
