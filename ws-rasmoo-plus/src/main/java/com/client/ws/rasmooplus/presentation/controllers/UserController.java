package com.client.ws.rasmooplus.presentation.controllers;

import com.client.ws.rasmooplus.domain.entities.UserEntity;
import com.client.ws.rasmooplus.presentation.dto.UserDTO;
import com.client.ws.rasmooplus.useCases.UserUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<UserEntity> create(@Valid  @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userUseCase.create(userDTO));
    }
}
