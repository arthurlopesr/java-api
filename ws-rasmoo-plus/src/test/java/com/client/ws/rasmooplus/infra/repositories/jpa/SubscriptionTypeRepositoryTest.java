package com.client.ws.rasmooplus.infra.repositories.jpa;

import com.client.ws.rasmooplus.domain.entities.jpa.SubscriptionsTypeEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@WebMvcTest(SubscriptionTypeRepository.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(profiles = "test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubscriptionTypeRepositoryTest {

    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository;

    @BeforeAll
    public void loadSubscriptions() {
        List<SubscriptionsTypeEntity> subscriptionsTypeEntityList = new ArrayList<>();
        SubscriptionsTypeEntity subscriptionsType1 = new SubscriptionsTypeEntity(null, "any_name1",
                null, BigDecimal.valueOf(100), "any_product_key1");
        subscriptionsTypeEntityList.add(subscriptionsType1);
       SubscriptionsTypeEntity subscriptionsType2 = new SubscriptionsTypeEntity(null, "any_name2",
                null, BigDecimal.valueOf(200), "any_product_key2");
        subscriptionsTypeEntityList.add(subscriptionsType2);
       SubscriptionsTypeEntity subscriptionsType3 = new SubscriptionsTypeEntity(null, "any_name3",
                null, BigDecimal.valueOf(300), "any_product_key3");
        subscriptionsTypeEntityList.add(subscriptionsType3);

        subscriptionTypeRepository.saveAll(subscriptionsTypeEntityList);
    }

    @AfterAll
    public void dropDataBase() {
        subscriptionTypeRepository.deleteAll();
    }

    @Test
    void given_findByProductKey_when_getProductKey_then_ReturnCorrectSubscriptionType() {
        assertEquals("any_name1", subscriptionTypeRepository.findByProductKey("any_product_key1").get().getName());
        assertEquals("any_name2", subscriptionTypeRepository.findByProductKey("any_product_key2").get().getName());
        assertEquals("any_name3", subscriptionTypeRepository.findByProductKey("any_product_key3").get().getName());
    }
}