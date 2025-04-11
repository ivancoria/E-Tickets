package com.ivancoria.etickets.exceptions.globalHandler;

import com.ivancoria.etickets.dtos.responses.ApiResponse;
import com.ivancoria.etickets.exceptions.customExceptions.NoDataChangedException;
import com.ivancoria.etickets.exceptions.customExceptions.PasswordMismatchException;
import com.ivancoria.etickets.exceptions.customExceptions.ResourceNotFoundException;
import com.ivancoria.etickets.exceptions.customExceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handlerResourceNotFoundException(ResourceNotFoundException ex){
        ApiResponse<String> apiResponse = ApiResponse
                .error(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        Map<String, String> mapErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String clave = ((FieldError) error).getField();
            String valor = error.getDefaultMessage();
            mapErrors.put(clave, valor);
        });
        ApiResponse<Map<String, String>> apiResponse = ApiResponse
                .error(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST","Errores", mapErrors);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handlerUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ApiResponse<String> apiResponse = ApiResponse
                .error(HttpStatus.CONFLICT.value(), "CONFLICT", ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ApiResponse<String>> handlerPasswordMismatchException(PasswordMismatchException ex) {
        ApiResponse<String> apiResponse = ApiResponse
                .error(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataChangedException.class)
    public ResponseEntity<ApiResponse<String>> handlerNoDataChangedException(NoDataChangedException ex) {
        ApiResponse<String> apiResponse = ApiResponse
                .error(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
