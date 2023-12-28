package com.example.data_api.exception;


import com.example.data_api.base.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.NoSuchFileException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseStatusException.class)
    public BaseError<?> handleServiceException(ResponseStatusException e){
        return BaseError.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .message("Something went wrong..!, please check.")
                .timestamp(LocalDateTime.now())
                .errors(e.getReason())
                .build();
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError<?> handleValidationException(MethodArgumentNotValidException e){

        List<Map<String, String>> errors = new ArrayList<>();
//        var errors = new ArrayList<>();

        for (FieldError error : e.getFieldErrors()){
//          var errorDetail = new HashMap<>();
            Map<String, String> errorDetail = new HashMap<>();
            errorDetail.put("name",error.getField());
            errorDetail.put("message",error.getDefaultMessage());
            errors.add(errorDetail);
        }

        return BaseError.builder()
                .status(false)
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation Error, please check detail message..!")
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .build();
    }
}
