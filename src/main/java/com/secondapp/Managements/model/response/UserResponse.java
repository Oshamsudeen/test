package com.secondapp.Managements.model.response;

import com.secondapp.Managements.model.entity.Role;
import com.secondapp.Managements.model.entity.User;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.Map;


public class UserResponse {

    private boolean status;
    private String name;
    private String ResponseMessage;
    private String email;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static UserResponse fromUser(User savedUser) {
        UserResponse response = new UserResponse();
        response.setStatus(false);
        response.setName(savedUser.getFirstName() + " " + savedUser.getSecondName());
        response.setResponseMessage("Successful");
        response.setEmail(savedUser.getEmail());
        return response;
    }
}
