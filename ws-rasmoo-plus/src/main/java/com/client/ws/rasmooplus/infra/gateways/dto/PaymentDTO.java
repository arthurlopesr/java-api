package com.client.ws.rasmooplus.infra.gateways.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
    private CreditCardDTO creditCard;

    private String customerId;

    private String orderId;
}
