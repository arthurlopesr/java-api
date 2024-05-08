package com.client.ws.rasmooplus.infra.gateways.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private CreditCardDTO creditCard;

    private String customerId;

    private String orderId;
}
