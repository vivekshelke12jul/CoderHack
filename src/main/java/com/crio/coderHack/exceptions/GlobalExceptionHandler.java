package com.crio.coderHack.exceptions;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDetails> handleResponseStatusException(ResponseStatusException exception, WebRequest req){

        HttpStatus status = (HttpStatus)exception.getStatusCode();

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
//                exception.getClass().getSimpleName(),
                exception.getBody().getDetail(),
                req.getDescription(false));

        return new ResponseEntity<>(errorDetails, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest req){

        HttpStatus status = (HttpStatus)exception.getStatusCode();
        FieldError error = exception.getBindingResult().getFieldErrors().getFirst();

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
//                exception.getClass().getSimpleName(),
                error.getDefaultMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, status);
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<ErrorDetails> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex) {
        String unknownField = ex.getPropertyName(); // Get the name of the unknown field
        String message = "Only score is updatable. Unknown property: '" + unknownField + "' in the request body. Please send only userId and score.";

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                message,
                null
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
