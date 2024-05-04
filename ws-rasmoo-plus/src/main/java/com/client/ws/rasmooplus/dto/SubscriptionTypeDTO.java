package com.client.ws.rasmooplus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionTypeDTO {
    private Long subscriptionsTypeId;

    private String name;

    private Integer accessMonths;

    private BigDecimal price;

    private String productKey;
}
