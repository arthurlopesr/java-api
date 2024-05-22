package com.client.ws.rasmooplus.useCases;

public interface TokenUseCase {
    String getToken(Long userId);

    Boolean isValid(String token);

    Long getUserId(String token);
}
