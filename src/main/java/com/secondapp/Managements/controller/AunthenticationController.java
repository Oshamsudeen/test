package com.secondapp.Managements.controller;

import com.secondapp.Managements.model.request.LoginRequest;
import com.secondapp.Managements.model.request.UserRequest;
import com.secondapp.Managements.model.response.UserResponse;
import com.secondapp.Managements.service.LoginService;
import com.secondapp.Managements.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class AunthenticationController {
    private final LoginService loginService;

    @Autowired
    public AunthenticationController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse login (@Valid @RequestBody LoginRequest request) {
        UserResponse response = loginService.loginUser(request);
        return response;
    }

}
