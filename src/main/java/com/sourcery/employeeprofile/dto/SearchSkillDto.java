package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SearchSkillDto {
    private String categories;
    private List<String> bottomSkills;
}
