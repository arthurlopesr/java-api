package com.client.ws.rasmooplus.domain.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {
    PROFESSOR(1L),
    ADMINISTRADOR(2L),
    ALUNO(3L);

    private final Long id;

    UserTypeEnum(Long id) {
        this.id = id;
    }
}
