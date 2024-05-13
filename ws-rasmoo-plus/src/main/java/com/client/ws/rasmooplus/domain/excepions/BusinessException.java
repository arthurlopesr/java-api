package com.client.ws.rasmooplus.domain.excepions;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
