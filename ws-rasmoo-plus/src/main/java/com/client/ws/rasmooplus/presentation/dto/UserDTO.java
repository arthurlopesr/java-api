package com.client.ws.rasmooplus.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long userId;

    @NotBlank(message = "field cannot be null or empty")
    @Size(min = 6, message = "minimum value of 6")
    private String name;

    @Email(message = "invalid")
    private String email;

    @Size(min = 11, message = "minimum value of 11")
    private String phone;

    @CPF(message = "invalid")
    private String cpf;

    private LocalDate dtSubscription = LocalDate.now();

    private LocalDate dtExpiration = LocalDate.now();

    @NotNull
    private Long userTypeId;

    private Long subscriptionsTypeId;
}
