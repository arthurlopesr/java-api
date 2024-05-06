package com.client.ws.rasmooplus.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTypeDTO {
    private Long userTypeId;

    @NotBlank(message = "field cannot be null or empty")
    @Size(min = 5, max = 30, message = "field size should be between 5 and 30")
    private String name;

    @Size(min = 5, max = 120, message = "field size should be between 5 and 120")
    private String description;
}
