package com.robotapocalypse.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponseDTO<T> {
    private boolean isSuccessful;
    private String message;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;
    private T data;

    public ApiResponseDTO(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        timeStamp = LocalDateTime.now();
    }
    public ApiResponseDTO(boolean isSuccessful, String message, T data) {
        this(isSuccessful, message);
        this.data = data;
    }
}
