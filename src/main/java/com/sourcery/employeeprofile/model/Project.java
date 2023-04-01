package com.sourcery.employeeprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    private UUID id;
    private String title;
    private Date startDate;
    private Date endDate;
    private String description;
}
