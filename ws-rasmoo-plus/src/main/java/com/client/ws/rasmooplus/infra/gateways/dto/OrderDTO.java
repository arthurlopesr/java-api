package com.client.ws.rasmooplus.infra.gateways.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String id;

    private String customerId;

    private BigDecimal discount;

    private String productAcronym;
}
