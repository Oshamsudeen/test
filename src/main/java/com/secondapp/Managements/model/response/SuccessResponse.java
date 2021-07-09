package com.secondapp.Managements.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class SuccessResponse extends ManagementApiResponse {

    private boolean status;
    private Map<String, Object> data;
}
