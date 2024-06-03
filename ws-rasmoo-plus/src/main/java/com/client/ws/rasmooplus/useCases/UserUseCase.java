package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.domain.entities.jpa.UserEntity;
import com.client.ws.rasmooplus.presentation.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserUseCase {
    UserEntity create(UserDTO userDTO);

    List<UserEntity> findAll();

    UserEntity findById(Long id);

    UserEntity update(Long id, UserDTO userDTO);

    void delete(Long id);

    UserEntity uploadPhoto(Long id, MultipartFile file) throws IOException;

    byte[] downloadPhoto(Long id);
}
