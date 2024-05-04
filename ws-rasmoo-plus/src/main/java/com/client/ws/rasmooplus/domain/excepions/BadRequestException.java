package com.client.ws.rasmooplus.domain.excepions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
