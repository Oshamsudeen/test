package com.secondapp.Managements.model.response;

import com.secondapp.Managements.model.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class RoleResponse {
    private Integer id;
    private String name;
    private String createdAt;
    private String updatedAt;

    public static RoleResponse fromRole(Role savedRole) {
        RoleResponse response = new RoleResponse();
        response.setId(savedRole.getId());
        response.setName(savedRole.getName());
        response.setCreatedAt(String.valueOf(savedRole.getCreatedAt()));
        response.setUpdatedAt(String.valueOf(savedRole.getUpdatedAt()));
        return response;
    }

}