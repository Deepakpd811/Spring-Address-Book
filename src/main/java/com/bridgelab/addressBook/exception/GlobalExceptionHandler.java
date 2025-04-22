package com.bridgelab.addressBook.exception;

import com.bridgelab.addressBook.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



    // Handle AddressBookException
    @ExceptionHandler(AddressBookException.class)
    public ResponseEntity<ApiResponse<String>> handleAddressBookException(AddressBookException ex) {
        ApiResponse<String> apiResponse = new ApiResponse<>(
                "error",
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
