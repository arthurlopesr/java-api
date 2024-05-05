package com.client.ws.rasmooplus.presentation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "field cannot be null or empty")
    @Size(min = 5, max = 30, message = "field size should be between 5 and 30")
    private String name;

    @Max(value = 12, message = "field cannot bet bigger then 12")
    private Integer accessMonths;

    @NotNull(message = "field cannot be null or empty")
    private BigDecimal price;

    @NotBlank(message = "field cannot be null or empty")
    @Size(min = 5, max = 15, message = "field size should be between 15 and 15")
    private String productKey;
}
