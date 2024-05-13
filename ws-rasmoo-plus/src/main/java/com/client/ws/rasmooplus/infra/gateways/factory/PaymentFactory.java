package com.client.ws.rasmooplus.infra.gateways.factory;

import com.client.ws.rasmooplus.infra.gateways.dto.CreditCardDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.PaymentDTO;

public class PaymentFactory {
    public static PaymentDTO build(String costumerId, String orderId, CreditCardDTO creditCardDTO) {
        return PaymentDTO.builder()
                .customerId(costumerId)
                .orderId(orderId)
                .creditCard(creditCardDTO)
                .build();
    }
}
