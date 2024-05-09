package com.client.ws.rasmooplus.presentation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPaymentInfoDTO {
    private Long userPaymentInfoId;

    @Size(min = 16, max = 16, message = "CardNumber must contain 16 characters")
    private String cardNumber;

    @Min(value = 1)
    @Max(value = 12)
    private Long cardExpirationMonth;

    private Long privateExpirationYear;

    @Size(min = 3, max = 3, message = "Card Security code must contain 3 characters")
    private String cardSecurityCode;

    private BigDecimal price;

    private Integer instalments;

    private LocalDate dtPayment = LocalDate.now();

    @NotNull(message = "UserId can't be null")
    private Long userId;
}
