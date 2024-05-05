package com.client.ws.rasmooplus.useCases.factory;

import com.client.ws.rasmooplus.domain.entities.SubscriptionsTypeEntity;
import com.client.ws.rasmooplus.domain.entities.UserEntity;
import com.client.ws.rasmooplus.domain.entities.UserTypeEntity;
import com.client.ws.rasmooplus.presentation.dto.UserDTO;

public class UserFactory {
    public static UserEntity fromDtoToEntity(UserDTO userDTO, UserTypeEntity userTypeEntity, SubscriptionsTypeEntity subscriptionsType) {
        return UserEntity.builder()
                .userId(userDTO.getUserId())
                .name(userDTO.getName())
                .cpf(userDTO.getCpf())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .dtSubscription(userDTO.getDtSubscription())
                .dtExpiration(userDTO.getDtExpiration())
                .userType(userTypeEntity)
                .subscriptionsType(subscriptionsType)
                .build();
    }
}
