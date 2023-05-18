package com.sourcery.employeeprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Builder
@Data
public class Notification {
    private Integer id;
    private Integer employeeId;
    private Integer projectId;
    private Integer initiatorEmployeeId;
    private String notificationType;
    private boolean read;
    private LocalDateTime notificationCreatedAt;
}
