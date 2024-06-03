package com.client.ws.rasmooplus.presentation.controllers;

import com.client.ws.rasmooplus.domain.entities.jpa.UserEntity;
import com.client.ws.rasmooplus.presentation.controllers.doc.UserControllerDoc;
import com.client.ws.rasmooplus.presentation.dto.UserDTO;
import com.client.ws.rasmooplus.useCases.UserUseCase;
import jakarta.validation.Valid;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerDoc {

    @Autowired
    private UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<UserEntity> create(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userUseCase.create(userDTO));
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userUseCase.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userUseCase.update(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PatchMapping(value = "/{id}/upload-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> uploadPhoto(@PathVariable("id") Long id, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(userUseCase.uploadPhoto(id, file));
    }

    @GetMapping(value = "/{id}/photo", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> downloadPhoto(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userUseCase.downloadPhoto(id));
    }
}
