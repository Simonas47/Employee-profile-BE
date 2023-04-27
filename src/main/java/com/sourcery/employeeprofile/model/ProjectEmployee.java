package com.sourcery.employeeprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectEmployee {
    private Integer id;
    private Integer projectId;
    private Integer employeeId;
    private String projectEmployeeStatus;
    private Date projectEmployeeStartDate;
    private Date projectEmployeeEndDate;
}
