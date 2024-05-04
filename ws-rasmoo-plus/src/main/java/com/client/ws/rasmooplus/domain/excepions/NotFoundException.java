package com.client.ws.rasmooplus.domain.excepions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
