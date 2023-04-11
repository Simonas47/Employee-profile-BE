package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.ProjectEmployee;
import com.sourcery.employeeprofile.model.Skill;
import com.sourcery.employeeprofile.model.SkillEmployee;
import com.sourcery.employeeprofile.repository.sqlprovider.ProjectSqlProvider;
import com.sourcery.employeeprofile.repository.sqlprovider.SkillSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface SkillsRepository {
    @Select("SELECT * FROM skills")
    List<Skill> getAll();

    @SelectProvider(type = SkillSqlProvider.class, method = "getSkillRelationshipsByEmployeeId")
    List<SkillEmployee> getSkillsByEmployeeId(@Param("employeeId") UUID employeeId);

    @Delete("DELETE FROM skills_employees WHERE skillId = #{skillId} AND employeeId = #{employeeId}")
    void deleteSkillEmployeeById(UUID employeeId, UUID skillId);

    @Insert("INSERT INTO skills_employees (skillId, skillLevel, employeeId) " +
            "VALUES (#{skillId}, CAST(#{skillLevel} AS SkillLevels), #{employeeId});")
    void insertSkillEmployee(UUID skillId, String skillLevel, UUID employeeId);
}
