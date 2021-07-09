package com.secondapp.Managements.model.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class RoleRequest {

    @Valid
    @NotNull
    private String name;

}