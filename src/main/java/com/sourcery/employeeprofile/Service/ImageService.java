package com.sourcery.employeeprofile.Service;
import com.sourcery.employeeprofile.Model.Image;
import com.sourcery.employeeprofile.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;


@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public Image uploadImage(MultipartFile file) throws IOException {
        Image imageModel = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .bytes(file.getBytes())
                .build();

        UUID newId = UUID.randomUUID();
        imageModel.setId(newId);
        imageRepository.create(imageModel);
        Image newImage = imageRepository.findById(newId);
        return newImage;
    }

    public Image findById(UUID id) {
        return imageRepository.findById(id);
    }

}
