package com.robotapocalypse.web.exception;

import com.robotapocalypse.data.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice(basePackages = {"com.robotapocalypse.web.controller"})
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO methodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();
        return buildErrorResponse(errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleAllUncaughtException(
            Exception e,
            WebRequest request){
        log.error("Unknown error occurred", e);
        return buildErrorResponse(e);
    }

    private ErrorDTO buildErrorResponse(List<FieldError> errors) {
        ErrorDTO errorDto = new ErrorDTO("98", "Validation error. Check 'errors' field for details.");

        for (FieldError fieldError : errors){
            errorDto.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errorDto;
    }

    private ErrorDTO buildErrorResponse(Exception e) {
        ErrorDTO errorDto = new ErrorDTO("500", e.getMessage());
        errorDto.setData("Unknown error occurred");
        return errorDto;
    }
}
