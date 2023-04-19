package com.sourcery.employeeprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProjectDto {
    private UUID id;
    private String title;
    private Date startDate;
    private Date endDate;
    private String description;
    private List<TeamMemberDto> teamMembers;
}
