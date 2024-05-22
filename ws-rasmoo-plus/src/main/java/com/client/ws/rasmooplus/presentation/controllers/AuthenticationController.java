package com.client.ws.rasmooplus.presentation.controllers;

import com.client.ws.rasmooplus.presentation.dto.LoginDTO;
import com.client.ws.rasmooplus.presentation.dto.TokenDTO;
import com.client.ws.rasmooplus.useCases.AuthenticationUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationUseCase authenticationUseCase;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationUseCase.auth(loginDTO));
    }
}
