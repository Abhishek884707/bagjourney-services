package com.abhishek.bagjourney_services.exception;

import com.abhishek.bagjourney_services.dto.ErrorResponse;
import com.abhishek.bagjourney_services.model.Constats;
import com.abhishek.bagjourney_services.model.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        if(Constats.BJ0001.equalsIgnoreCase(ex.getMessage())){
            ErrorCodes error = ErrorCodes.BJ0001;
            errorResponse.setErrorCode(error.getCode());
            errorResponse.setErrorMessage(error.getMessage());
        }else{
            errorResponse.setErrorCode(Constats.BJ0500);
            errorResponse.setErrorMessage(ex.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCodes.BJ0500.getCode());
        errorResponse.setErrorMessage(ErrorCodes.BJ0500.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

}
