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
public class ProjectEmployee {
    private UUID id;
    private UUID projectId;
    private UUID employeeId;
    private String startDate;
    private String endDate;
    private String teamMemberStatus;
}
