package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Mapper
public interface ImageRepository {
    @Insert("INSERT INTO images (id, name, type, bytes) " +
            "VALUES (#{id}, #{name}, #{type}, #{bytes});")
    void create(Image image);

    @Select("SELECT * FROM images WHERE id=#{id};")
    Image findById(UUID id);
}
