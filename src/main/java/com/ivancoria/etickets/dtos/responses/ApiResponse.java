package com.ivancoria.etickets.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/* Al incluir "JsonInclude.NON_NULL" la respuesta JSON no enviará datos null: "error", "data" */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    public String error;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(LocalDateTime.now(), status, null, message, data);
    }

    public static <T> ApiResponse<T> success(int status, String message) {
        return new ApiResponse<>(LocalDateTime.now(), status, null,  message, null);
    }

    public static <T> ApiResponse<T> error(int status,String error, String message, T data) {
        return new ApiResponse<>(LocalDateTime.now(), status, error, message, data);
    }

    public static <T> ApiResponse<T> error(int status,String error, String message) {
        return new ApiResponse<>(LocalDateTime.now(), status, error, message, null);
    }
}
