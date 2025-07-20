package com.abhishek.bagjourney_services.model;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCodes {

    BJ0001("BJ0001", "Query Parameter value validation"),
    BJ0500("BJ0500", "Internal Server Error");
    private static final ErrorCodes[] VALUES = values();
    String code;
    String message;

    ErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
