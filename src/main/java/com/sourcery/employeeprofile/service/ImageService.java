package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.model.Image;
import com.sourcery.employeeprofile.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public Image createNewImage(MultipartFile file) throws IOException {
        Image imageModel = Image
                .builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .bytes(Base64.getEncoder().encodeToString(file.getBytes()))
                .build();
        imageRepository.createNewImage(imageModel);
        return imageRepository.getById(imageModel.getId());
    }

    public Image getById(Integer id) {
        return imageRepository.getById(id);
    }
}
