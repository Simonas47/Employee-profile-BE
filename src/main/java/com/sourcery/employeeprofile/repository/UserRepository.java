package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.dto.UserDto;
import com.sourcery.employeeprofile.model.User;
import com.sourcery.employeeprofile.repository.sqlprovider.UsersSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface UserRepository {
    @InsertProvider(type = UsersSqlProvider.class, method = "createNewUser")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createNewUser(User employee);

    @SelectProvider(type = UsersSqlProvider.class, method = "getByEmailAndPassword")
    Optional<UserDto> getByEmailAndPassword(@Param("email") String email, @Param("password") String password);


}
