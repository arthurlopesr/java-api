package com.client.ws.rasmooplus.infra.gateways.integration.impl;

import com.client.ws.rasmooplus.infra.gateways.dto.CostumerDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.OrderDTO;
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
        OrderDTO orderDTO = new OrderDTO(null, "663abe74cc69af734bd2e915", BigDecimal.ZERO, "MONTH22");
        wsRaspayIntegration.createOrder(orderDTO);
    }
}
