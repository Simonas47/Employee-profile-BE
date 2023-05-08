package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.NotificationDto;
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
    public List<NotificationDto> getAllByEmployeeId(Integer employeeId) {
        return mapModelsToDtos(notificationRepository.getAllByEmployeeId(employeeId));
    }

    public void setReadById(Integer id, boolean read) {
        notificationRepository.setReadById(id, read);
    }

    public void createNotification(Notification notification) {
        notificationRepository.createNotification(notification);
    }





}
