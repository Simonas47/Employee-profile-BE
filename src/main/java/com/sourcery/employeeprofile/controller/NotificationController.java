package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.NotificationDto;
import com.sourcery.employeeprofile.model.Notification;
import com.sourcery.employeeprofile.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/setReadById/{id}/{read}")
    public void setReadById(@PathVariable Integer id, @PathVariable boolean read) {
        notificationService.setReadById(id, read);
    }

    @PostMapping("/createNotification")
    public void createNotification(@RequestBody Notification notification) {
        notificationService.createNotification(notification);
    }
}
