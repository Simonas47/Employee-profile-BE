package com.sourcery.employeeprofile.mapper;

import com.sourcery.employeeprofile.dto.NotificationDto;
import com.sourcery.employeeprofile.model.Notification;
import com.sourcery.employeeprofile.service.EmployeeService;
import com.sourcery.employeeprofile.service.ProjectService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sourcery.employeeprofile.repository.sqlprovider.EmployeeSqlProvider.getById;

public class NotificationMapper {

    public static List<NotificationDto> mapModelsToDtos(List<Notification> allEmployeeNotifications) {
        List<NotificationDto> outputList = new ArrayList<>();
        for (Notification employeeNotification : allEmployeeNotifications) {
            if (employeeNotification.isRead()) continue;

//            List<Integer> ids = Arrays.asList(
//                    employeeNotification.getEmployeeId(),
//                    employeeNotification.getInitiatorEmployeeId()
//            );
//
//            if (
//                    ids.stream().anyMatch(id -> employeeService.getById(id).isEmpty()) ||
//                    projectService.getProjectById(employeeNotification.getProjectId()).isEmpty()
//            ) continue;

            NotificationDto notificationDto = new NotificationDto(
                    employeeNotification.getId(),
                    employeeNotification.getEmployeeId(),
                    employeeNotification.getProjectId(),
                    employeeNotification.getInitiatorEmployeeId(),
                    employeeNotification.getNotificationType(),
                    employeeNotification.getNotificationCreatedAt()
            );
            outputList.add(notificationDto);
        }
        return outputList;
    }
}
