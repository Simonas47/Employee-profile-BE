package com.sourcery.employeeprofile.mybatis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


@Alias("MapToJson")
public class MapToJsonTypeHandler extends BaseTypeHandler<Map<String, ?>> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, ?> parameter, JdbcType jdbcType)
            throws SQLException {
        try {
            PGobject jsonObject = new PGobject();
            jsonObject.setType("json");
            jsonObject.setValue(mapper.writeValueAsString(parameter));
            ps.setObject(i, jsonObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize to json", e);
        }
    }

    @Override
    public Map<String, ?> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return stringToMap(rs.getString(columnName));
    }

    @Override
    public Map<String, ?> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return stringToMap(rs.getString(columnIndex));
    }

    @Override
    public Map<String, ?> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return stringToMap(cs.getString(columnIndex));
    }


    private Map<String, ?> stringToMap(String str) {
        if (str == null) {
            return null;
        }
        try {
            return mapper.readValue(str, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse json", e);
        }
    }

}
