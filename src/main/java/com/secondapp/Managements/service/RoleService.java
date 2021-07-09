package com.secondapp.Managements.service;

import com.secondapp.Managements.exception.BadRequestException;
import com.secondapp.Managements.exception.NotFoundException;
import com.secondapp.Managements.model.entity.Role;
import com.secondapp.Managements.model.request.RoleRequest;
import com.secondapp.Managements.model.response.RoleResponse;
import com.secondapp.Managements.model.response.SuccessResponse;
import com.secondapp.Managements.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public SuccessResponse createRole(RoleRequest request) {
        if (request.getName().equals("")){
            throw new BadRequestException("Role name is required");
        }
        Role role = new Role();
        role.setName(request.getName());
        Role savedRole = roleRepository.save(role);
        return buildSuccessResponse(createRoleResponse(savedRole));
    }

    public SuccessResponse updateRole(Integer roleId, RoleRequest request) {
        Role alreadySavedRole = fetchRoleById(roleId);
        alreadySavedRole.setName(request.getName());
        Role savedRole = roleRepository.save(alreadySavedRole);
        return buildSuccessResponse(createRoleResponse(savedRole));
    }

    private Map<String, Object> createRoleResponse(Role savedRole) {
        Map<String, Object> data = new HashMap<>();
        data.put("role", RoleResponse.fromRole(savedRole));
        return data;
    }

    private Role fetchRoleById(Integer roleId) {
        Role alreadySavedRole = roleRepository.findOneById(roleId);
        if(alreadySavedRole == null) {
            throw new NotFoundException("role with this id does not exist");
        }
        return alreadySavedRole;
    }

    private SuccessResponse buildSuccessResponse(Map<String, Object> data) {
        return SuccessResponse.builder().status(true).data(data).build();
    }

}
