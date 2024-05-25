package com.client.ws.rasmooplus.useCases.factory;

import com.client.ws.rasmooplus.domain.entities.jpa.UserEntity;
import com.client.ws.rasmooplus.domain.entities.jpa.UserPaymentInfoEntity;
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
                .installments(userPaymentInfoDTO.getInstallments())
                .user(user)
                .build();
    }

}
