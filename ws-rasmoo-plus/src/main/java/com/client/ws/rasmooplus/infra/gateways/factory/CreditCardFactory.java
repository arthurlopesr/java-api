package com.client.ws.rasmooplus.infra.gateways.factory;

import com.client.ws.rasmooplus.infra.gateways.dto.CreditCardDTO;
import com.client.ws.rasmooplus.presentation.dto.UserPaymentInfoDTO;

public class CreditCardFactory {

    public static CreditCardDTO build(UserPaymentInfoDTO userPaymentInfoDTO, String documentNumber) {
        return CreditCardDTO.builder()
                .documentNumber(documentNumber)
                .cvv(Long.parseLong(userPaymentInfoDTO.getCardSecurityCode()))
                .number(userPaymentInfoDTO.getCardNumber())
                .month(userPaymentInfoDTO.getCardExpirationMonth())
                .year(userPaymentInfoDTO.getCardExpirationYear())
                .installments(userPaymentInfoDTO.getInstallments())
                .build();
    }
}
