package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PutReqSkill {
    private boolean checked;
    private String skillLevel;
    private UUID skillId;
    private UUID employeeId;
}
