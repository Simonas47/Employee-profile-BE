package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.repository.sqlprovider.EmployeeSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface EmployeeRepository {
    @InsertProvider(type = EmployeeSqlProvider.class, method = "createNewEmployee")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createNewEmployee(Employee employee);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "getById")
    Optional<EmployeeDto> getById(@Param("id") UUID id);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "getEmployees")
    List<EmployeeDto> getEmployees(@Param("name") String name, @Param("page") Integer page, @Param("size") Integer size);
}
