package com.client.ws.rasmooplus.infra.gateways.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostumerDTO {
    private String id;

    private String email;

    private String cpf;

    private String firstName;

    private String lastName;

}
