package com.client.ws.rasmooplus.infra.gateways.integration;

import com.client.ws.rasmooplus.infra.gateways.dto.CostumerDTO;
import com.client.ws.rasmooplus.infra.gateways.integration.impl.WsRaspayIntegrationImpl;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WsRaspayIntegrationTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WsRaspayIntegrationImpl wsRaspayIntegration;

    private static HttpHeaders headers;

    @BeforeAll
    public static void loadHeaders() {
        headers = getHttpHeaders();
    }

    @Test
    void give_createCustomer_when_apiResponseIs201Created_then_returnCustomerDto() {
        ReflectionTestUtils.setField(wsRaspayIntegration, "raspayHost", "http://localhost:8081/ws-raspay");
        ReflectionTestUtils.setField(wsRaspayIntegration, "customerUrl", "/costumer");
        CostumerDTO costumerDTO = new CostumerDTO();
        costumerDTO.setCpf("11111111111");
        HttpEntity<CostumerDTO> request = new HttpEntity<>(costumerDTO, this.headers);

        when(restTemplate.exchange("http://localhost:8081/ws-raspay/costumer", HttpMethod.POST, request, CostumerDTO.class))
                .thenReturn(ResponseEntity.of(Optional.of(costumerDTO)));
        Assertions.assertEquals(costumerDTO, wsRaspayIntegration.createCostumer(costumerDTO));;

        verify(restTemplate, times(1)).exchange("http://localhost:8081/ws-raspay" + "/costumer", HttpMethod.POST, request, CostumerDTO.class);
    }

    @Test
    void give_createCustomer_when_apiResponseIs400BadRequest_then_returnNull() {
        ReflectionTestUtils.setField(wsRaspayIntegration, "raspayHost", "http://localhost:8081/ws-raspay");
        ReflectionTestUtils.setField(wsRaspayIntegration, "customerUrl", "/costumer");
        CostumerDTO costumerDTO = new CostumerDTO();
        costumerDTO.setCpf("11111111111");
        HttpEntity<CostumerDTO> request = new HttpEntity<>(costumerDTO, this.headers);

        when(restTemplate.exchange("http://localhost:8081/ws-raspay/costumer", HttpMethod.POST, request, CostumerDTO.class))
                .thenReturn(ResponseEntity.badRequest().build());
        Assertions.assertNull(wsRaspayIntegration.createCostumer(costumerDTO));

        verify(restTemplate, times(1)).exchange("http://localhost:8081/ws-raspay" + "/costumer", HttpMethod.POST, request, CostumerDTO.class);
    }

    @Test
    void give_createCustomer_when_apiResponseIsGetThrows_then_throwError() {
        ReflectionTestUtils.setField(wsRaspayIntegration, "raspayHost", "http://localhost:8081/ws-raspay");
        ReflectionTestUtils.setField(wsRaspayIntegration, "customerUrl", "/costumer");
        CostumerDTO costumerDTO = new CostumerDTO();
        costumerDTO.setCpf("11111111111");
        HttpEntity<CostumerDTO> request = new HttpEntity<>(costumerDTO, this.headers);

        when(restTemplate.exchange("http://localhost:8081/ws-raspay/costumer", HttpMethod.POST, request, CostumerDTO.class))
                .thenThrow(RuntimeException.class);
        Assertions.assertThrows(Exception.class, () -> wsRaspayIntegration.createCostumer(costumerDTO));

        verify(restTemplate, times(1)).exchange("http://localhost:8081/ws-raspay" + "/costumer", HttpMethod.POST, request, CostumerDTO.class);
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