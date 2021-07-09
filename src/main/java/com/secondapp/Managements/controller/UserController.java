package com.secondapp.Managements.controller;

import com.secondapp.Managements.model.request.UserRequest;
import com.secondapp.Managements.model.response.ManagementApiResponse;
import com.secondapp.Managements.model.response.SuccessResponse;
import com.secondapp.Managements.model.response.UserResponse;
import com.secondapp.Managements.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagementApiResponse> create (@Valid @RequestBody UserRequest request) {
        SuccessResponse response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/user/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<ManagementApiResponse>  update(@PathVariable String email, @RequestBody UserRequest request){
        SuccessResponse response = userService.UpdateUser(email, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}