package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.NotificationDto;
import com.sourcery.employeeprofile.dto.NotificationRequestDto;
import com.sourcery.employeeprofile.mapper.NotificationMapper;
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

    public void setReadById(Integer id, boolean read) {
        notificationRepository.setReadById(id, read);
    }

    public void createNotification(NotificationRequestDto notificationRequestDto) {
        Notification notification = new Notification(
                notificationRequestDto.getId(),
                notificationRequestDto.getEmployeeId(),
                notificationRequestDto.getProjectId(),
                notificationRequestDto.getInitiatorEmployeeId(),
                notificationRequestDto.getNotificationType(),
                notificationRequestDto.isRead(),
                notificationRequestDto.getNotificationCreatedAt());
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
