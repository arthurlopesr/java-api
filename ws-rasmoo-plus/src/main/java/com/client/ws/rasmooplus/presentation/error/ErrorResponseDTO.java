package com.client.ws.rasmooplus.presentation.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponseDTO {
    private String message;

    private HttpStatus httpStatus;

    private Integer statusCode;
}
