package com.client.ws.rasmooplus.infra.gateways.integration.impl;

import com.client.ws.rasmooplus.infra.gateways.dto.CostumerDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.OrderDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.PaymentDTO;
import com.client.ws.rasmooplus.infra.gateways.integration.WsRaspayIntegration;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Component
public class WsRaspayIntegrationImpl implements WsRaspayIntegration {

    @Value("${webservices.raspay.host}")
    private String raspayHost;
    
    @Value("${webservices.raspay.v1.customer}")
    private String customerUrl;
    
    @Value("${webservices.raspay.v1.order}")
    private String orderUrl;
    
    @Value("${webservices.raspay.v1.payment}")
    private String paymentUrl;

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public WsRaspayIntegrationImpl() {
        restTemplate = new RestTemplate();
        headers = getHttpHeaders();
    }

    @Override
    public CostumerDTO createCostumer(CostumerDTO costumerDTO) {
        try {
            HttpEntity<CostumerDTO> request = new HttpEntity<>(costumerDTO, this.headers);
            ResponseEntity<CostumerDTO> response =
                    restTemplate.exchange(raspayHost+customerUrl, HttpMethod.POST, request, CostumerDTO.class);
            return response.getBody();
        } catch (Exception error) {
            throw error;
        }
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        try {
            HttpEntity<OrderDTO> request = new HttpEntity<>(orderDTO, this.headers);
            ResponseEntity<OrderDTO> response =
                    restTemplate.exchange(raspayHost+orderUrl, HttpMethod.POST, request, OrderDTO.class);
            return response.getBody();
        } catch (Exception error) {
            throw error;
        }
    }

    @Override
    public Boolean processPayment(PaymentDTO paymentDTO) {
        try {
            HttpEntity<PaymentDTO> request = new HttpEntity<>(paymentDTO, this.headers);
            ResponseEntity<Boolean> response =
                    restTemplate.exchange(raspayHost+paymentUrl, HttpMethod.POST, request, Boolean.class);
            return response.getBody();
        } catch (Exception error) {
            throw error;
        }
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String credential = "rasmooplus:r@sm00";
        byte[] bytes = credential.getBytes(StandardCharsets.UTF_8);
        byte[] encodedBytes = Base64.encodeBase64(bytes, false);
        String base64 = new String(encodedBytes, StandardCharsets.UTF_8);
        headers.add("Authorization", "basic " + base64);
        return headers;
    }
}
