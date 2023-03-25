package com.sourcery.employeeprofile.Repository;

import com.sourcery.employeeprofile.Dto.EmployeeDto;
import com.sourcery.employeeprofile.Model.Employee;
import org.apache.ibatis.annotations.*;
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
            " WHERE e.id=#{id} "
    )
    Optional<EmployeeDto> findById(UUID id);


    @Insert("INSERT INTO employees (name, surname, middleName, hiringDate, exitDate, titleId, imageId) " +
            "VALUES (#{name}, #{surname}, #{middleName}, #{hiringDate}, #{exitDate}, #{titleId}, #{imageId});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(Employee employee);

    @Select(" SELECT e.id, e.name, e.surname, e.middleName,e.hiringDate, e.exitDate, t.title, i.name as imageName, i.type as imageType, i.bytes as imageBytes " +
            " FROM employees e " +
            " LEFT JOIN titles t on e.titleId = t.id " +
            " LEFT JOIN images i on e.imageId = i.id "
    )
    List<EmployeeDto> getAll();
}
