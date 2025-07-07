package com.abhishek.bagjourney_services.dto;

import com.abhishek.bagjourney_services.model.ErrorCodes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    String errorCode;
    String errorMessage;
}
