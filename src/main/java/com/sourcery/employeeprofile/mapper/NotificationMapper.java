package com.sourcery.employeeprofile.mapper;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.NotificationDto;
import com.sourcery.employeeprofile.dto.ProjectDto;
import com.sourcery.employeeprofile.model.Notification;
import com.sourcery.employeeprofile.service.EmployeeService;
import com.sourcery.employeeprofile.service.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotificationMapper {

    public static List<NotificationDto> mapModelsToDtos(List<Notification> allEmployeeNotifications, EmployeeService employeeService, ProjectService projectService) {
        List<NotificationDto> outputList = new ArrayList<>();
        for (Notification employeeNotification : allEmployeeNotifications) {
            if (employeeNotification.isRead()) continue;

            Optional<EmployeeDto> initiatorEmployee = employeeService.getById(employeeNotification.getInitiatorEmployeeId());
            if (initiatorEmployee.isEmpty()) {
                throw new RuntimeException("no 'initiatorEmployee' found.");
            }
            Optional<ProjectDto> project = projectService.getProjectById(employeeNotification.getProjectId());
            if (project.isEmpty()) {
                throw new RuntimeException("no 'project' found.");
            }

            NotificationDto notificationDto = new NotificationDto(
                    employeeNotification.getId(),
                    employeeNotification.getEmployeeId(),
                    projectService.getProjectById(employeeNotification.getProjectId()).get(),
                    employeeService.getById(employeeNotification.getInitiatorEmployeeId()).get(),
                    employeeNotification.getNotificationType(),
                    employeeNotification.getNotificationCreatedAt()
            );
            outputList.add(notificationDto);
        }
        return outputList;
    }
}
