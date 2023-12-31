package com.sourcery.employeeprofile.dto;

import com.sourcery.employeeprofile.enums.NotificationTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NotificationDto {
    private Integer id;
    private Integer employeeId;
    private ProjectDto project;
    private EmployeeDto initiatorEmployee;
    private NotificationTypes notificationType;
    private boolean isRead;
    private LocalDateTime notificationCreatedAt;
}
