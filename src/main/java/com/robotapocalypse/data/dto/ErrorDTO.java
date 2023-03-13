package com.robotapocalypse.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@RequiredArgsConstructor
public class ErrorDTO {
    private final String statusCode;

    private final String message;

    private String stackTrace;

    private List<ValidationError> errors;

    private Object data;

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class ValidationError {
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message){
        if(isNull(errors)){
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }
}
