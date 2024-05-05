package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.domain.entities.UserEntity;
import com.client.ws.rasmooplus.presentation.dto.UserDTO;

public interface UserUseCase {
    UserEntity create(UserDTO userDTO);
}
