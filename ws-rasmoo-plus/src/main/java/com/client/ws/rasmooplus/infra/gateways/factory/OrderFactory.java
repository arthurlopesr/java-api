package com.client.ws.rasmooplus.infra.gateways.factory;

import com.client.ws.rasmooplus.infra.gateways.dto.OrderDTO;
import com.client.ws.rasmooplus.presentation.dto.PaymentProcessDTO;

public class OrderFactory {

    OrderFactory() {
    }

    public static OrderDTO orderFactoy(String customerId, PaymentProcessDTO processDTO) {
        return OrderDTO.builder()
                .customerId(customerId)
                .productAcronym(processDTO.getProductKey())
                .discount(processDTO.getDiscount())
                .build();
    }
}
