package com.client.ws.rasmooplus.infra.gateways.integration.impl;

import com.client.ws.rasmooplus.infra.gateways.dto.CostumerDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.CreditCardDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.OrderDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.PaymentDTO;
import com.client.ws.rasmooplus.infra.gateways.integration.MailIntegration;
import com.client.ws.rasmooplus.infra.gateways.integration.WsRaspayIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class MailIntegrationImplTest {

    @Autowired
    private MailIntegration mailIntegration;

    @Test
    void sendEmail() {
        mailIntegration.send("arthurlopr12@gmail.com", "Test GMAIL", "Acesso Liberado");
    }
}