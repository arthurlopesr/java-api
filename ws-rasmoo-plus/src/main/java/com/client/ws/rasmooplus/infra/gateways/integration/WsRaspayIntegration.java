package com.client.ws.rasmooplus.infra.gateways.integration;

import com.client.ws.rasmooplus.infra.gateways.dto.CostumerDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.OrderDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.PaymentDTO;

public interface WsRaspayIntegration {

    CostumerDTO createCostumer(CostumerDTO costumerDTO);

    OrderDTO createOrder(OrderDTO orderDTO);

    Boolean processPayment(PaymentDTO paymentDTO);

}
