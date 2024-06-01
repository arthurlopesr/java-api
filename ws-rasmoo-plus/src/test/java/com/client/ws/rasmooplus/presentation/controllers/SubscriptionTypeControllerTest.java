package com.client.ws.rasmooplus.presentation.controllers;

import com.client.ws.rasmooplus.domain.entities.jpa.SubscriptionsTypeEntity;
import com.client.ws.rasmooplus.presentation.dto.SubscriptionTypeDTO;
import com.client.ws.rasmooplus.useCases.SubscriptionTypeUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@WebMvcTest(SubscriptionTypeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(profiles = "test")
class SubscriptionTypeControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscriptionTypeUseCase subscriptionTypeUseCase;

    @Test
    void given_findAll_then_returnAllSubscriptionTypes() throws Exception {
        mockMvc.perform(get("/subscription-type"))
                .andExpect(status().isOk());
    }

    @Test
    void given_findById_then_returnGetSubscriptionTypes() throws Exception {
        SubscriptionsTypeEntity subscriptionsType = new SubscriptionsTypeEntity(1L, "any_name", null, BigDecimal.valueOf(999), "any_product_key");
        when(subscriptionTypeUseCase.findById(1L)).thenReturn(subscriptionsType);
        mockMvc.perform(get("/subscription-type/1"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subscriptionsTypeId", is(1)));
    }

    @Test
    void given_delete_then_returnNoContent() throws Exception {
        mockMvc.perform(delete("/subscription-type/1")).andExpect(status().isNoContent());

        verify(subscriptionTypeUseCase, times(1)).delete(1L);
    }

    @Test
    void given_create_then_returnSubscriptionTypeCreated() throws Exception {
        SubscriptionsTypeEntity subscriptionsType = new SubscriptionsTypeEntity(1L, "any_name", null, BigDecimal.valueOf(999), "any_product_key");
        SubscriptionTypeDTO subscriptionTypeDTO = new SubscriptionTypeDTO(null, "any_name", null, BigDecimal.valueOf(999), "any_product_key");

        when(subscriptionTypeUseCase.create(subscriptionTypeDTO)).thenReturn(subscriptionsType);
        mockMvc.perform(post("/subscription-type")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(subscriptionTypeDTO)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void given_create_whenDtoIsMissingValues_then_returnBadRequest() throws Exception {
        SubscriptionTypeDTO subscriptionTypeDTO = new SubscriptionTypeDTO(null, "", 13, null, "22");

        mockMvc.perform(post("/subscription-type")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(subscriptionTypeDTO)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verify(subscriptionTypeUseCase, times(0)).create(any());
    }


    @Test
    void given_update_then_returnSubscriptionTypeUpdated() throws Exception {
        SubscriptionsTypeEntity subscriptionsType = new SubscriptionsTypeEntity(1L, "any_name", null, BigDecimal.valueOf(999), "any_product_key");
        SubscriptionTypeDTO subscriptionTypeDTO = new SubscriptionTypeDTO(null, "any_name", null, BigDecimal.valueOf(999), "any_product_key");

        when(subscriptionTypeUseCase.update(1L, subscriptionTypeDTO)).thenReturn(subscriptionsType);
        mockMvc.perform(put("/subscription-type/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(subscriptionTypeDTO)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void given_update_whenDtoIsMissingValues_then_returnBadRequest() throws Exception {
        SubscriptionTypeDTO subscriptionTypeDTO = new SubscriptionTypeDTO(null, "", 13, null, "22");

        mockMvc.perform(put("/subscription-type/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(subscriptionTypeDTO)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verify(subscriptionTypeUseCase, times(0)).update(any(), any());
    }
}