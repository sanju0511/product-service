package com.ecommerce.microservice.exception;

import com.ecommerce.microservice.dto.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionMessage> productNotFoundException(ProductNotFoundException exception){
        ExceptionMessage exceptionMessage=new ExceptionMessage();
        exceptionMessage.setMessage(exception.getMessage());
        exceptionMessage.setTimeStamp(LocalDate.now());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        ExceptionMessage exceptionMessage=new ExceptionMessage();
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        exceptionMessage.setTimeStamp(LocalDate.now());
        exceptionMessage.setMessage("Validation Failed");
        exceptionMessage.setValidationErrors(errors);

        return new ResponseEntity<>(exceptionMessage,HttpStatus.BAD_REQUEST);
    }

}
