package coe.unosquare.exception;

import coe.unosquare.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> illegalArgsExceptionHandler(IllegalArgumentException ex){
        ApiResponse response = new ApiResponse(false,"Invalid input", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handler for other non handled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> GeneralExceptionHandler(Exception ex){
        ApiResponse response = new ApiResponse(false, "Order processing failed",ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // TODO Generate more exception handlers here...

}
