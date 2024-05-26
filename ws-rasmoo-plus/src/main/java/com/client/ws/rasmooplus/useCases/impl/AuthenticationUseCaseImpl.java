package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.jpa.UserCredentialsEntity;
import com.client.ws.rasmooplus.domain.excepions.BadRequestException;
import com.client.ws.rasmooplus.presentation.dto.LoginDTO;
import com.client.ws.rasmooplus.presentation.dto.TokenDTO;
import com.client.ws.rasmooplus.useCases.AuthenticationUseCase;
import com.client.ws.rasmooplus.useCases.TokenUseCase;
import com.client.ws.rasmooplus.useCases.UserDetailsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {
    @Autowired
    private UserDetailsUseCase userDetailsUseCase;

    @Autowired
    private TokenUseCase tokenUseCase;

    @Override
    public TokenDTO auth(LoginDTO dto) {
        try {
            UserCredentialsEntity userCredentials = userDetailsUseCase.loadUserByUsernameAndPass(dto.getUsername(), dto.getPassword());
            String token = tokenUseCase.getToken(userCredentials.getUserCredentialsId());
            return TokenDTO.builder().token(token).type("Bearer").build();
        } catch (Exception e) {
            throw new BadRequestException("Token error - "+e.getMessage());
        }
    }
}
