package com.secondapp.Managements.controller;

import com.secondapp.Managements.model.request.RoleRequest;
import com.secondapp.Managements.model.request.UserRequest;
import com.secondapp.Managements.model.response.ManagementApiResponse;
import com.secondapp.Managements.model.response.RoleResponse;
import com.secondapp.Managements.model.response.SuccessResponse;
import com.secondapp.Managements.model.response.UserResponse;
import com.secondapp.Managements.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagementApiResponse> create(@Valid @RequestBody RoleRequest request) {
        SuccessResponse response = roleService.createRole(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping(path = "/roles/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagementApiResponse> update(@PathVariable Integer roleId, @RequestBody RoleRequest request){
        SuccessResponse response = roleService.updateRole(roleId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @PostMapping(path = "/createusers", produces = MediaType.APPLICATION_JSON_VALUE)
//    public RoleResponse create(@Valid @RequestBody RoleRequest request) {
//        UserResponse response = roleService.createRole();
//        return response;
//    }
}
