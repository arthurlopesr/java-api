package com.client.ws.rasmooplus.useCase;

import com.client.ws.rasmooplus.useCases.UserDetailsUseCase;
import com.client.ws.rasmooplus.useCases.UserUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserUseCaseTest {

    @Autowired
    private UserDetailsUseCase userDetailsUseCase;

    @Test
    void contextLoad() {
        userDetailsUseCase.sendRecoveryCode("arthurlopr12@gmail.com");
    }
}
