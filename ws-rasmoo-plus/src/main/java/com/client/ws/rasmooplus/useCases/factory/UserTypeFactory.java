package com.client.ws.rasmooplus.useCases.factory;

import com.client.ws.rasmooplus.domain.entities.UserTypeEntity;
import com.client.ws.rasmooplus.presentation.dto.UserTypeDTO;

public class UserTypeFactory {
    private UserTypeFactory() {
    }

    public static UserTypeEntity fromDtoToEntity(UserTypeDTO userTypeDTO) {
        return UserTypeEntity.builder()
                .userTypeId(userTypeDTO.getUserTypeId())
                .name(userTypeDTO.getName())
                .description(userTypeDTO.getDescription())
                .build();
    }
}
