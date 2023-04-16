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
public class TeamMemberDto {
    private UUID id;
    private String name;
    private String surname;
    private String middleName;
    private String title;
    private String status;
    private String imageType;
    private String imageBytes;
    private String teamMemberStatus;
    private Date teamMemberStartDate;
    private Date teamMemberEndDate;
}
