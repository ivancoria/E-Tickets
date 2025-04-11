package com.ivancoria.etickets.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoDataChangedException extends RuntimeException {
    public NoDataChangedException(String message) {
        super(message);
    }
}
