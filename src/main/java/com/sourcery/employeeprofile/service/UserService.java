package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.UserDto;
import com.sourcery.employeeprofile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<UserDto> getByEmailAndPassword(String email, String password) {
        return userRepository.getByEmailAndPassword(email, password);
    }
}