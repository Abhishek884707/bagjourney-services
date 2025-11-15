package com.abhishek.bagjourney_services.exception;

import com.abhishek.bagjourney_services.dto.ErrorResponse;
import com.abhishek.bagjourney_services.dto.ValidationError;
import com.abhishek.bagjourney_services.model.Constats;
import com.abhishek.bagjourney_services.model.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSuccess(false);
        errorResponse.setErrorCode(ErrorCodes.BJ0001.getCode());
        List<ValidationError> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                {
                    errors.add(ValidationError.builder().errorField(error.getField())
                            .errorMessage(error.getDefaultMessage()).build());
                }
        );
        errorResponse.setErrors(errors);
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }


    @ExceptionHandler(BagJourneyValidationException.class)
    public ResponseEntity<ErrorResponse> handleBagJourneyException(BagJourneyValidationException ex){
        List<ValidationError> errors = new ArrayList<>();
        ErrorCodes error = ErrorCodes.BJ0001;
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSuccess(false);
        errorResponse.setErrorCode(error.getCode());
        errors.add(ValidationError.builder().errorField(ex.getField())
                    .errorMessage(ex.getMessage()).build());
        errorResponse.setErrors(errors);
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        List<ValidationError> errors = new ArrayList<>();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSuccess(false);
        errorResponse.setErrorCode(Constats.BJ0500);
        errors.add(ValidationError.builder()
                .errorMessage(ex.getMessage()).build());
        errorResponse.setErrors(errors);
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

}
