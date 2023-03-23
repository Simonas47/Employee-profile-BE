package com.sourcery.employeeprofile.Repository;

import com.sourcery.employeeprofile.Model.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.UUID;

public interface ImageRepository {
    @Insert("INSERT INTO images (id, name, type, bytes) " +
            "VALUES (#{id}, #{name}, #{type}, #{bytes});")
    void create(Image image);

    @Select("SELECT * FROM images WHERE id=#{id};")
    Image findById(UUID id);
}
