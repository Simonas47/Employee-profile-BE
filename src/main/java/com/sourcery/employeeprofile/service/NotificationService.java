package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.NotificationDto;
import com.sourcery.employeeprofile.dto.NotificationRequestDto;
import com.sourcery.employeeprofile.model.Notification;
import com.sourcery.employeeprofile.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sourcery.employeeprofile.mapper.NotificationMapper.mapModelsToDtos;


@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProjectService projectService;

    public List<NotificationDto> getAllByEmployeeId(Integer employeeId) {
        return mapModelsToDtos(notificationRepository.getAllByEmployeeId(employeeId), employeeService, projectService);
    }

    public void setIsReadById(Integer id, boolean isRead) {
        notificationRepository.setIsReadById(id, isRead);
    }

    public void setIsReadByEmployeeId(Integer employeeId, boolean isRead) {
        notificationRepository.setIsReadByEmployeeId(employeeId, isRead);
    }
    public void createNotification(NotificationRequestDto notificationRequestDto) {
        Notification notification = Notification.builder()
                .id(notificationRequestDto.getId())
                .employeeId(notificationRequestDto.getEmployeeId())
                .projectId(notificationRequestDto.getProjectId())
                .initiatorEmployeeId(notificationRequestDto.getInitiatorEmployeeId())
                .notificationType(notificationRequestDto.getNotificationType())
                .isRead(notificationRequestDto.isRead())
                .notificationCreatedAt(notificationRequestDto.getNotificationCreatedAt())
                .build();
        for (Integer employeeId: notificationRequestDto.getEmployeeIds()) {
            if (employeeId.equals(notification.getInitiatorEmployeeId())) continue;
            notification.setEmployeeId(employeeId);
            notificationRepository.createNotification(notification);
        }
    }

    public void deleteByProjectId(Integer projectId) {
        notificationRepository.deleteByProjectId(projectId);
    }
}
