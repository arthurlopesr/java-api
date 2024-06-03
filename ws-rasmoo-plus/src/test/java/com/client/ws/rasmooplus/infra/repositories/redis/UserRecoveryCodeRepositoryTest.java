package com.client.ws.rasmooplus.infra.repositories.redis;

import com.client.ws.rasmooplus.domain.entities.redis.UserRecoveryCode;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.AutoConfigureDataRedis;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureDataRedis
@AutoConfigureTestDatabase
@WebMvcTest(UserRecoveryCodeRepository.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(profiles = "test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRecoveryCodeRepositoryTest {

    @Autowired
    private UserRecoveryCodeRepository userRecoveryCodeRepository;

    @BeforeAll
    public void loadSubscriptions() {
        List<UserRecoveryCode> userRecoveryCodes = new ArrayList<>();
        UserRecoveryCode userRecoveryCode1 = new UserRecoveryCode();
        userRecoveryCode1.setEmail("test1@test.com");
        userRecoveryCode1.setCode("1234");
        userRecoveryCodes.add(userRecoveryCode1);

        UserRecoveryCode userRecoveryCode2 = new UserRecoveryCode();
        userRecoveryCode2.setEmail("test2@test.com");
        userRecoveryCode2.setCode("2345");
        userRecoveryCodes.add(userRecoveryCode2);

        UserRecoveryCode userRecoveryCode3 = new UserRecoveryCode();
        userRecoveryCode3.setEmail("test3@test.com");
        userRecoveryCode3.setCode("6789");
        userRecoveryCodes.add(userRecoveryCode3);

        userRecoveryCodeRepository.saveAll(userRecoveryCodes);
    }

    @AfterAll
    public void dropDataBase() {
        userRecoveryCodeRepository.deleteAll();
    }

    @Test
    void given_findByEmail_when_getByEmail_then_returnCorrectUserRecoveryCode() {
        assertEquals("1234", userRecoveryCodeRepository.findByEmail("test1@test.com").get().getCode());
        assertEquals("2345", userRecoveryCodeRepository.findByEmail("test2@test.com").get().getCode());
        assertEquals("6789", userRecoveryCodeRepository.findByEmail("test3@test.com").get().getCode());
    }
}