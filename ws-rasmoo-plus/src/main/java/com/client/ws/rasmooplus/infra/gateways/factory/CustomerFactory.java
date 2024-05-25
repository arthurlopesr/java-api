package com.client.ws.rasmooplus.infra.gateways.factory;

import com.client.ws.rasmooplus.domain.entities.jpa.UserEntity;
import com.client.ws.rasmooplus.infra.gateways.dto.CostumerDTO;

public class CustomerFactory {

    CustomerFactory() {
    }

    public static CostumerDTO factoryCostumer(UserEntity user) {
        var fullName = user.getName().split(" ");
        var fistName = fullName[0];
        var lastName = fullName.length > 1 ? fullName[fullName.length - 1] : "";

        return CostumerDTO.builder()
                .email(user.getEmail())
                .firstName(fistName)
                .lastName(lastName)
                .cpf(user.getCpf())
                .build();
    }
}
