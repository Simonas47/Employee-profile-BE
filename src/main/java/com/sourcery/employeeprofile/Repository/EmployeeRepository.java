package com.sourcery.employeeprofile.Repository;
import com.sourcery.employeeprofile.Dto.EmployeeDto;
import com.sourcery.employeeprofile.Model.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface EmployeeRepository {

    @Select(" SELECT e.id, e.name, e.surname, e.middle_name,e.hiring_date, e.exit_date, t.title, i.name as image_name, i.type as image_type, i.bytes as image_bytes " +
            " FROM employees e " +
            " LEFT JOIN titles t on e.title_id = t.id "+
            " LEFT JOIN images i on e.image_id = i.id "+
            " WHERE e.id=#{id} "
    )
    @Results( value = {
            @Result(property = "middle_name", column = "middle_name"),
            @Result(property = "exit_date", column = "exit_date"),
            @Result(property = "hiring_date", column = "hiring_date"),
            @Result(property = "image_name", column = "image_name"),
            @Result(property = "image_type", column = "image_type"),
            @Result(property = "image_bytes", column = "image_bytes"),
    })
    Optional<EmployeeDto> findById(UUID id);


    @Insert("INSERT INTO employees (id, name, surname, middle_name, hiring_date, exit_date, title_id, image_id) " +
            "VALUES (#{id}, #{name}, #{surname}, #{middle_name}, #{hiring_date}, #{exit_date}, #{title_id}, #{image_id});")
    void create(Employee employee);
}
