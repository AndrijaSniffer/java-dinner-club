package com.ates.dinnerClub.handlers;

import com.ates.dinnerClub.classes.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(e.getClass().getName(), "Requested resource not found")
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleConflict(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(e.getClass().getName(), "Duplicate entry, invalid data or deletion not possible due to foreign key constraints")
        );
    }

    // Temporary exception handler for invalid requests
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(e.getClass().getName(), e.getMessage())
        );
    }

    // Handler for inner class property exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(e.getClass().getName(), String.format("Field: %s; Error: %s", e.getBindingResult().getFieldErrors().getFirst().getField(),
                        e.getBindingResult().getFieldErrors().getFirst().getDefaultMessage()))
        );
    }

    // Handler for controller method parameter validation
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(HandlerMethodValidationException e) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(e.getClass().getName(), e.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponse(e.getClass().getName(), "Something went wrong")
        );
    }
}
