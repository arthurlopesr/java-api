package com.client.ws.rasmooplus.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Invalid password")
    private String password;

    private String recoveryCode;
}
