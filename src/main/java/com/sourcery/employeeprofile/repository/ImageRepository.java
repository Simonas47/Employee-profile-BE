package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.Image;
import com.sourcery.employeeprofile.repository.sqlprovider.ImageSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface ImageRepository {
    @InsertProvider(type = ImageSqlProvider.class, method = "createNewImage")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createNewImage(Image image);

    @SelectProvider(type = ImageSqlProvider.class, method = "getById")
    Image getById(Integer id);
}
