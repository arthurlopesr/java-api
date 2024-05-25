package com.client.ws.rasmooplus.presentation.controllers;

import com.client.ws.rasmooplus.domain.entities.jpa.UserTypeEntity;
import com.client.ws.rasmooplus.presentation.dto.UserTypeDTO;
import com.client.ws.rasmooplus.useCases.UserTypeUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-type")
public class UserTypeController {

    @Autowired
    private UserTypeUseCase userTypeUseCase;

    @GetMapping
    public ResponseEntity<List<UserTypeEntity>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userTypeUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTypeEntity> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userTypeUseCase.findById(id));
    }

    @PostMapping()
    public ResponseEntity<UserTypeEntity> create(@Valid @RequestBody UserTypeDTO userTypeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userTypeUseCase.create(userTypeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserTypeEntity> update(@PathVariable("id") Long id, @RequestBody UserTypeDTO userTypeDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userTypeUseCase.update(id, userTypeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userTypeUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
