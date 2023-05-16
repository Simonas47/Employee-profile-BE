package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.Title;
import com.sourcery.employeeprofile.repository.sqlprovider.TitleSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TitleRepository {
    @SelectProvider(type = TitleSqlProvider.class, method = "getAllTitles")
    List<Title> getAllTitles();

}
