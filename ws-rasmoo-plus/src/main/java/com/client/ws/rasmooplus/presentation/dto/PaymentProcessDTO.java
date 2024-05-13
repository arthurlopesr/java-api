package com.client.ws.rasmooplus.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentProcessDTO {

    @NotBlank(message = "ProductKey most be informed")
    private String productKey;

    private BigDecimal discount;

    @NotNull(message = "UserPaymentInfo most be informed")
    @JsonProperty("userPaymentInfo")
    private UserPaymentInfoDTO userPaymentInfoDTO;
}
