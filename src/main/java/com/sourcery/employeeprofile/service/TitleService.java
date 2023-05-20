package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.model.Title;
import com.sourcery.employeeprofile.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {
    @Autowired
    TitleRepository titleRepository;

    public List<Title> getAllTitles() {
        return titleRepository.getAllTitles();
    }
}
