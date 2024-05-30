package com.client.ws.rasmooplus.presentation.controllers;

import com.client.ws.rasmooplus.domain.entities.jpa.UserTypeEntity;
import com.client.ws.rasmooplus.useCases.UserTypeUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@WebMvcTest(UserTypeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(profiles = "test")
class UserTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserTypeUseCase userTypeUseCase;

    @Test
    void given_findAll_then_returnAllUserType() throws Exception {
        List<UserTypeEntity> userTypeList = new ArrayList<>();
        UserTypeEntity firstUser = new UserTypeEntity(1L, "any_name", "any_description");
        UserTypeEntity secondUser = new UserTypeEntity(2L, "any_name", "any_description");
        userTypeList.add(firstUser);
        userTypeList.add(secondUser);

        when(userTypeUseCase.findAll()).thenReturn(userTypeList);
        mockMvc.perform(MockMvcRequestBuilders.get("/user-type"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}