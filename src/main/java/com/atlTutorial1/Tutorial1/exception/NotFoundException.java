package com.atlTutorial1.Tutorial1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@NoArgsConstructor
public class NotFoundException extends GlobalException{
    private String message;

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
        this.message=message;
    }

}
