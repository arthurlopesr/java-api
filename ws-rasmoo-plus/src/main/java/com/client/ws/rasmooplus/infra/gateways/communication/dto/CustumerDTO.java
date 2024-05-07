package com.client.ws.rasmooplus.infra.gateways.communication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustumerDTO {
    private String id;

    private String email;

    private String cpf;

    private String fisrtName;

    private String lastName;

}
