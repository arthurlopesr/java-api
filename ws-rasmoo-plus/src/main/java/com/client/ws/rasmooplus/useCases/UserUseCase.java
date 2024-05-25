package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.domain.entities.jpa.UserEntity;
import com.client.ws.rasmooplus.presentation.dto.UserDTO;

import java.util.List;

public interface UserUseCase {
    UserEntity create(UserDTO userDTO);

    List<UserEntity> findAll();

    UserEntity findById(Long id);

    UserEntity update(Long id, UserDTO userDTO);

    void delete(Long id);
}
