package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.NotificationDto;
import com.sourcery.employeeprofile.dto.NotificationRequestDto;
import com.sourcery.employeeprofile.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/notifications")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/getAllByEmployeeId/{employeeId}")
    public List<NotificationDto> getAllByEmployeeId(@PathVariable Integer employeeId) {
        return notificationService.getAllByEmployeeId(employeeId);
    }

    @PutMapping("/setIsReadById/{id}/{isRead}")
    public void setIsReadById(@PathVariable Integer id, @PathVariable boolean isRead) {
        notificationService.setIsReadById(id, isRead);
    }

    @PutMapping("/setIsReadByEmployeeId/{employeeId}/{isRead}")
    public void setIsReadByEmployeeId(@PathVariable Integer employeeId, @PathVariable boolean isRead) {
        notificationService.setIsReadByEmployeeId(employeeId, isRead);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createNotifications")
    public void createNotification(@RequestBody NotificationRequestDto notificationRequestDto) {
        notificationService.createNotification(notificationRequestDto);
    }
}
