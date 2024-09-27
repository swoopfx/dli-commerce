package com.sb.ecommerce.exceptions;

import com.sb.ecommerce.doa.GenericError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
//@ExceptionHandler
public class MyGlobalException{
    private MyGlobalException() {
    }

    public static MyGlobalException createMyGlobalException() {
        return new MyGlobalException();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError)err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(response,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GenericError> myHttpMessageNotReadableException(HttpMessageNotReadableException e){
//        Map<String, String> response = new HashMap<>();
//        e.getBindingResult().getAllErrors().forEach(err -> {
//            String fieldName = ((FieldError)err).getField();
//            String message = err.getDefaultMessage();
//            response.put(fieldName,message);
//        });
        GenericError error = new GenericError();
        error.setMessage("Body is required");
//        error.setKey("message");
        return new ResponseEntity<GenericError>(error,
                HttpStatus.BAD_REQUEST);
    }
}
