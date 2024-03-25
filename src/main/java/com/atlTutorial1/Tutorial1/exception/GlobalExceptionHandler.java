package com.atlTutorial1.Tutorial1.exception;

import com.atlTutorial1.Tutorial1.dto.ErrorDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorDto> handleException(GlobalException e){
        return ResponseEntity.status(e.getStatus()).body(new ErrorDto(e.getStatus(), e.getMessage()));
    }
}
