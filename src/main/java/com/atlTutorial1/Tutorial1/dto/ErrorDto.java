package com.atlTutorial1.Tutorial1.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorDto {
    private HttpStatus status;
    private String message;
    private final LocalDateTime timeStamp = LocalDateTime.now();

    public ErrorDto(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
