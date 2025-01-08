package com.crio.coderHack.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime timestamp;
//    private String status;           //404 NOT_FOUND
//    private String exception_name;  //MethodArgumentNotValidException
    private String message;         //User not found with id : 1
    private String path;            //      /users/1
}
