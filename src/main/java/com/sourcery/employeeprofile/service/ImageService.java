package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.model.Image;
import com.sourcery.employeeprofile.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public Image createNewImage(MultipartFile file) throws IOException {
        Image imageModel = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .bytes(file.getBytes())
                .build();
        UUID newId = UUID.randomUUID();
        imageModel.setId(newId);
        imageRepository.createNewImage(imageModel);
        return imageRepository.getById(newId);
    }

    public Image getById(UUID id) {
        return imageRepository.getById(id);
    }
}
