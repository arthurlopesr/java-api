package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.domain.entities.jpa.UserTypeEntity;
import com.client.ws.rasmooplus.infra.repositories.jpa.UserTypeRepository;
import com.client.ws.rasmooplus.useCases.impl.UserTypeUseCaseImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserTypeUseCaseTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @InjectMocks
    private UserTypeUseCaseImpl userTypeUseCase;

    @Test
    void give_findAll_when_thereAreDataInDataBase_then_returnAllData() {
        List<UserTypeEntity> userTypeList = new ArrayList<>();
        UserTypeEntity firstUser = new UserTypeEntity(1L, "any_name", "any_description");
        UserTypeEntity secondUser = new UserTypeEntity(2L, "any_name", "any_description");
        userTypeList.add(firstUser);
        userTypeList.add(secondUser);

        Mockito.when(userTypeRepository.findAll()).thenReturn(userTypeList);
        var result = userTypeUseCase.findAll();
        Assertions.assertThat(result).isNotEmpty().hasSize(2);
    }
}
