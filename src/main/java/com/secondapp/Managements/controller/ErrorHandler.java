package com.secondapp.Managements.controller;

import com.secondapp.Managements.exception.BadRequestException;
import com.secondapp.Managements.exception.NotFoundException;
import com.secondapp.Managements.exception.ProcessingException;
import com.secondapp.Managements.model.constant.ErrorCode;
import com.secondapp.Managements.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            HttpServletRequest request, Exception e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(false)
                .error(ErrorCode.INPUT)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ProcessingException.class)
    public ResponseEntity<ErrorResponse> handleProcessing
            (HttpServletRequest request, ProcessingException e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(false)
                .error(ErrorCode.PROCESSING)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound
            (HttpServletRequest request, NotFoundException e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(false)
                .error(ErrorCode.NOT_FOUND)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalError
            (HttpServletRequest request, Exception e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(false)
                .error(ErrorCode.PROCESSING)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
