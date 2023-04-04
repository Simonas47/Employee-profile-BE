package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.UserDto;
import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.model.Image;
import com.sourcery.employeeprofile.model.User;
import com.sourcery.employeeprofile.repository.EmployeeRepository;
import com.sourcery.employeeprofile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto createNewUser(User user) throws IOException {
        userRepository.createNewUser(user);
        return this.getById(user.getId()).orElseThrow(IllegalStateException::new);
    }

    public Optional<UserDto> getById(UUID id) {
        return userRepository.getById(id);
    }

    public Optional<UserDto> getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public Optional<UserDto> getByEmailAndPassword(String email, String password) {
        return userRepository.getByEmailAndPassword(email, password);
    }

    public List<UserDto> getUsers(String searchValue, Integer page, Integer size) {
        String nameLike = "%" + searchValue + "%";
        return userRepository.getUsers(nameLike, page, size);
    }

    public Integer getUsersCountByEmail(String searchValue) {
        String nameLike = "%" + searchValue + "%";
        return userRepository.getUsersCountByEmail(nameLike);
    }
}