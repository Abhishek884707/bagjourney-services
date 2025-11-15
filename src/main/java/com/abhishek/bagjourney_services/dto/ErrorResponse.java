package com.abhishek.bagjourney_services.dto;

import com.abhishek.bagjourney_services.model.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    boolean success;
    String errorCode;
    List<ValidationError> errors;
}
