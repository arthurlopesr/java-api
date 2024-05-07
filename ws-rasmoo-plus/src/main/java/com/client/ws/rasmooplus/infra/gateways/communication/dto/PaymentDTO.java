package com.client.ws.rasmooplus.infra.gateways.communication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private CreditCardDTO creditCard;

    private String custumerId;

    private String orderId;
}
