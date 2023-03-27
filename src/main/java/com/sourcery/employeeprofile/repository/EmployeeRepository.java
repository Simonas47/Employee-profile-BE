package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.model.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface EmployeeRepository {

    @Select(" SELECT e.id, e.name, e.surname, e.middleName,e.hiringDate, e.exitDate, t.title, i.name as imageName, i.type as imageType, i.bytes as imageBytes " +
            " FROM employees e " +
            " LEFT JOIN titles t on e.titleId = t.id " +
            " LEFT JOIN images i on e.imageId = i.id " +
            " WHERE e.id=#{id} ")
    Optional<EmployeeDto> findById(UUID id);

    @Insert("INSERT INTO employees (name, surname, middleName, hiringDate, exitDate, titleId, imageId) " +
            "VALUES (#{name}, #{surname}, #{middleName}, #{hiringDate}, #{exitDate}, #{titleId}, #{imageId});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(Employee employee);

    @Select(" SELECT e.id, e.name, e.surname, e.middleName,e.hiringDate, e.exitDate, t.title, i.name as imageName, i.type as imageType, i.bytes as imageBytes " +
            " FROM employees e " +
            " LEFT JOIN titles t on e.titleId = t.id " +
            " LEFT JOIN images i on e.imageId = i.id " +
            " WHERE LOWER(e.name) LIKE #{searchValue} OR LOWER(e.surname) LIKE #{searchValue} OR LOWER(e.middleName) LIKE #{searchValue} " +
            " ORDER BY e.name ASC ")
    List<EmployeeDto> getAllByName(String searchValue);
}
