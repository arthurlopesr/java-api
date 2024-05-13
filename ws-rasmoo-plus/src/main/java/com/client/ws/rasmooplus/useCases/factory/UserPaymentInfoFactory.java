package com.client.ws.rasmooplus.useCases.factory;

import com.client.ws.rasmooplus.domain.entities.UserEntity;
import com.client.ws.rasmooplus.domain.entities.UserPaymentInfoEntity;
import com.client.ws.rasmooplus.presentation.dto.UserPaymentInfoDTO;

public class UserPaymentInfoFactory {

    private UserPaymentInfoFactory() {
    }

    public static UserPaymentInfoEntity fromDtoToEntity(UserPaymentInfoDTO userPaymentInfoDTO, UserEntity user) {
        return UserPaymentInfoEntity.builder()
                .userPaymentInfoId(userPaymentInfoDTO.getUserPaymentInfoId())
                .cardNumber(userPaymentInfoDTO.getCardNumber())
                .cardExpirationMonth(userPaymentInfoDTO.getCardExpirationMonth())
                .cardExpirationYear(userPaymentInfoDTO.getCardExpirationYear())
                .cardSecurityCode(userPaymentInfoDTO.getCardSecurityCode())
                .price(userPaymentInfoDTO.getPrice())
                .dtPayment(userPaymentInfoDTO.getDtPayment())
                .user(user)
                .build();
    }

}
