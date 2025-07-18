package com.project.expenseTrackerAPI.backend.controller;

import com.project.expenseTrackerAPI.backend.customException.BadRequestException;
import com.project.expenseTrackerAPI.backend.customException.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice(annotations = RestController.class)
public class ErrorController {

    @ExceptionHandler
    public ResponseEntity<String> handleDataModelError(BadRequestException ex){
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleDataModelNotFoundError(NotFoundException ex){
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleDeserializationError(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .badRequest()
                .body("Some types are incorrect in Expenses fields");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handlePathVarTypeError(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity
                .badRequest()
                .body("Incorrect Path Variables");
    }

}
