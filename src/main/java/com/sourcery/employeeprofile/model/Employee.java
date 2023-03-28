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
public class Employee {
    private UUID id;
    private String name;
    private String surname;
    private String middleName;
    private Date hiringDate;
    private Date exitDate;
    private UUID imageId;
    private UUID titleId;
}
