package com.sourcery.employeeprofile.dto;

import com.sourcery.employeeprofile.model.Notification;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NotificationRequestDto {
    private List<Integer> employeeIds;
    private Integer id;
    private Integer employeeId;
    private Integer projectId;
    private Integer initiatorEmployeeId;
    private String notificationType;
    private boolean read;
    private LocalDateTime notificationCreatedAt;
}
