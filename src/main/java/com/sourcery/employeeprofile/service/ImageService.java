package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.model.Image;
import com.sourcery.employeeprofile.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public Image createNewImage(Image image) throws IOException {
        imageRepository.createNewImage(image);
        return imageRepository.getById(image.getId());
    }

    public Image getById(Integer id) {
        return imageRepository.getById(id);
    }
}
