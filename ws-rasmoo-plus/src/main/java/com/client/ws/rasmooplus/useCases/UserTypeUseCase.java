package com.client.ws.rasmooplus.useCases;


import com.client.ws.rasmooplus.domain.entities.UserTypeEntity;
import com.client.ws.rasmooplus.presentation.dto.UserTypeDTO;

import java.util.List;

public interface UserTypeUseCase {
    List<UserTypeEntity> findAll();

    UserTypeEntity findById(Long id);

    UserTypeEntity create(UserTypeDTO userTypeDTO);

    UserTypeEntity update(Long id, UserTypeDTO userTypeDTO);

    void delete(Long id);
}
