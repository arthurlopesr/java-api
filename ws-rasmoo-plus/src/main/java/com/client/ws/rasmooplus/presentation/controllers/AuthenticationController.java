package com.client.ws.rasmooplus.presentation.controllers;

import com.client.ws.rasmooplus.domain.entities.redis.UserRecoveryCode;
import com.client.ws.rasmooplus.presentation.dto.LoginDTO;
import com.client.ws.rasmooplus.presentation.dto.TokenDTO;
import com.client.ws.rasmooplus.useCases.AuthenticationUseCase;
import com.client.ws.rasmooplus.useCases.UserDetailsUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationUseCase authenticationUseCase;

    @Autowired
    private UserDetailsUseCase userDetailsUseCase;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationUseCase.auth(loginDTO));
    }

    @PostMapping("/recovery-code/send")
    public ResponseEntity<?> sendRecoveryCode(@RequestBody @Valid UserRecoveryCode userRecoveryCode) {
        userDetailsUseCase.sendRecoveryCode(userRecoveryCode.getEmail());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/recovery-code/")
    public ResponseEntity<?> recoveryCodeIsValid(@RequestParam("recoveryCode") String recoveryCode,
                                                 @RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userDetailsUseCase.recoveryCodeIsValid(recoveryCode, email));
    }
}
