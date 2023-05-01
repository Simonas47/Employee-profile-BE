package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.ProjectEmployeeDto;
import com.sourcery.employeeprofile.dto.SearchEmployeeDto;
import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.repository.sqlprovider.EmployeeSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface EmployeeRepository {
    @InsertProvider(type = EmployeeSqlProvider.class, method = "createNewEmployee")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createNewEmployee(Employee employee);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "getById")
    Optional<EmployeeDto> getById(@Param("id") Integer id);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "getByEmail")
    Optional<EmployeeDto> getByEmail(@Param("email") String email);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "getEmployees")
    List<SearchEmployeeDto> getEmployees(@Param("name") String name,
                                         @Param("page") Integer page,
                                         @Param("pageSize") Integer pageSize,
                                         @Param("isLimited") Boolean isLimited);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "getEmployeeCountByName")
    Integer getEmployeeCountByName(@Param("name") String name);

    @SelectProvider(type = EmployeeSqlProvider.class, method = "getProjectEmployeesByProjectId")
    List<ProjectEmployeeDto> getProjectEmployeesByProjectId(@Param("projectId") Integer projectId);
}
