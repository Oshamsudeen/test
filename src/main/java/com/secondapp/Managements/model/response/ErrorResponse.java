package com.secondapp.Managements.model.response;

import com.secondapp.Managements.model.constant.ErrorCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse extends ManagementApiResponse {

    private boolean status;
    private ErrorCode error;
    private String message;
}
