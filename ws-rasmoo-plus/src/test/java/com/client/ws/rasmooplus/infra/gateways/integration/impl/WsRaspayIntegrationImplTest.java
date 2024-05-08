package com.client.ws.rasmooplus.infra.gateways.integration.impl;

import com.client.ws.rasmooplus.infra.gateways.dto.CostumerDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.CreditCardDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.OrderDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.PaymentDTO;
import com.client.ws.rasmooplus.infra.gateways.integration.WsRaspayIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class WsRaspayIntegrationImplTest {

    @Autowired
    private WsRaspayIntegration wsRaspayIntegration;

    @Test
    void createCostumerWhenDtoOk() {
        CostumerDTO costumerDTO = new CostumerDTO(null, "test@test.com", "39201257031", "Arthur", "Lopes");
        wsRaspayIntegration.createCostumer(costumerDTO);
    }

    @Test
    void createOrder() {
        OrderDTO orderDTO = new OrderDTO(null, "663ba368d61dac357cfe9975", BigDecimal.ZERO, "MONTH22");
        wsRaspayIntegration.createOrder(orderDTO);
    }

    @Test
    void processPayment() {
        CreditCardDTO creditCardDTO = new CreditCardDTO(
                123L,
                "39201257031",
                0L,
                06L,
                "12341234123412341234",
                2025L
        );
        PaymentDTO paymentDTO = new PaymentDTO(creditCardDTO, "663ba368d61dac357cfe9975","663ba3f7d61dac357cfe9978");
        wsRaspayIntegration.processPayment(paymentDTO);
    }
}