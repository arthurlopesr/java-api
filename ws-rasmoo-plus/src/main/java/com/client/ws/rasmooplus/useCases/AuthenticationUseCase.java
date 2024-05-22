package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.presentation.dto.LoginDTO;
import com.client.ws.rasmooplus.presentation.dto.TokenDTO;

public interface AuthenticationUseCase {
    TokenDTO auth(LoginDTO loginDTO);
}
