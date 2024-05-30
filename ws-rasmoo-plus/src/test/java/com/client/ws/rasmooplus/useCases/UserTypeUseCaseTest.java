package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.domain.entities.jpa.UserCredentialsEntity;
import com.client.ws.rasmooplus.domain.entities.jpa.UserEntity;
import com.client.ws.rasmooplus.domain.entities.jpa.UserTypeEntity;
import com.client.ws.rasmooplus.domain.entities.redis.UserRecoveryCode;
import com.client.ws.rasmooplus.domain.excepions.BadRequestException;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.domain.utils.PasswordUtils;
import com.client.ws.rasmooplus.infra.gateways.integration.MailIntegration;
import com.client.ws.rasmooplus.infra.repositories.jpa.UserDetailsRepository;
import com.client.ws.rasmooplus.infra.repositories.jpa.UserTypeRepository;
import com.client.ws.rasmooplus.infra.repositories.redis.UserRecoveryCodeRepository;
import com.client.ws.rasmooplus.presentation.dto.UserDTO;
import com.client.ws.rasmooplus.useCases.impl.UserDetailsUseCaseImpl;
import com.client.ws.rasmooplus.useCases.impl.UserTypeUseCaseImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserTypeUseCaseTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Mock
    private UserRecoveryCodeRepository userRecoveryCodeRepository;

    @Mock
    private MailIntegration mailIntegration;

    @InjectMocks
    private UserTypeUseCaseImpl userTypeUseCase;

    @InjectMocks
    private UserDetailsUseCaseImpl userDetailsUseCase;

    private UserDTO userDTO;

    private UserEntity user;

    private UserTypeEntity userType;

    private UserCredentialsEntity userCredentials;

    private UserRecoveryCode userRecoveryCode;

    @Captor
    private ArgumentCaptor<UserRecoveryCode> userRecoveryCodeCaptor;

    @BeforeEach
    public void mockedInfos() {
        userDTO = new UserDTO();
        userDTO.setEmail("test@test.com");
        userDTO.setCpf("11111111111");
        userDTO.setName("any_name");
        userDTO.setUserTypeId(1L);

        userType = new UserTypeEntity(1L, "user_type_name", "user_type_description");

        user = new UserEntity();
        user.setUserId(1L);
        user.setName("any_name");
        user.setEmail("test@test.com");
        user.setCpf("11111111111");
        user.setUserType(userType);

        userCredentials = new UserCredentialsEntity();
        userCredentials.setPassword("any_password");
        userCredentials.setUserType(userType);
        userCredentials.setUserCredentialsId(1L);
        userCredentials.setUsername("any_username");

        userRecoveryCode = new UserRecoveryCode();
        userRecoveryCode.setCode("any_code");
        userRecoveryCode.setEmail("test@test.com");
    }

    @Test
    void given_getUserCredentials_when_shouldReturnTrue_when_PasswordMatches() {
        mockStatic(PasswordUtils.class);
        String username = userCredentials.getUsername();
        String password = "hashed_password";

        when(userDetailsRepository.findByUsername(username)).thenReturn(Optional.of(userCredentials));
        when(PasswordUtils.matches(password, userCredentials.getPassword())).thenReturn(true);

        UserCredentialsEntity result = userDetailsUseCase.loadUserByUsernameAndPass(username, password);
        Assertions.assertEquals(userCredentials, result);
        verify(userDetailsRepository, times(1)).findByUsername(username);
        PasswordUtils.matches(password, userCredentials.getPassword());
    }

    @Test
    void given_getUserCredentials_when_shouldReturnFalse_when_PasswordMatchesThrows() {
        String username = userCredentials.getUsername();
        String password = "hashed_password";

        when(userDetailsRepository.findByUsername(username)).thenReturn(Optional.of(userCredentials));
        when(PasswordUtils.matches(password, userCredentials.getPassword())).thenReturn(false);

        Assertions.assertThrows(BadRequestException.class, () -> userDetailsUseCase.loadUserByUsernameAndPass(username, password));
    }

    @Test
    void give_findAll_when_thereAreDataInDataBase_then_returnAllData() {
        List<UserTypeEntity> userTypeList = new ArrayList<>();
        UserTypeEntity firstUser = new UserTypeEntity(1L, "any_name", "any_description");
        UserTypeEntity secondUser = new UserTypeEntity(2L, "any_name", "any_description");
        userTypeList.add(firstUser);
        userTypeList.add(secondUser);

        when(userTypeRepository.findAll()).thenReturn(userTypeList);
        var result = userTypeUseCase.findAll();
        Assertions.assertEquals(userTypeList, result);
    }

    @Test
    void given_sendRecoveryCode_when_shouldSendRecoveryCodeWhenUserRecoveryCodeOptIsEmpty_then_return_recoveryCode() {
        String email = user.getEmail();
        when(userRecoveryCodeRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(userDetailsRepository.findByUsername(email)).thenReturn(Optional.of(new UserCredentialsEntity()));

        userDetailsUseCase.sendRecoveryCode(email);

        verify(userRecoveryCodeRepository).save(userRecoveryCodeCaptor.capture());
        UserRecoveryCode savedCode = userRecoveryCodeCaptor.getValue();
        Assertions.assertNotNull(savedCode);
        Assertions.assertEquals(email, savedCode.getEmail());
        Assertions.assertNotNull(savedCode.getCode());
        Assertions.assertEquals(4, savedCode.getCode().length());
        Assertions.assertNotNull(savedCode.getCreatedAt());

        verify(mailIntegration).send(eq(email), anyString(), anyString());
    }

    @Test
    void givenEmailWithExistingRecoveryCode_whenSendRecoveryCode_thenUpdatesRecoveryCodeAndSendsEmail() {
        String email = "test@example.com";
        UserRecoveryCode existingCode = new UserRecoveryCode();
        existingCode.setEmail(email);
        existingCode.setCode("1234");
        existingCode.setCreatedAt(LocalDateTime.now().minusDays(1));

        when(userRecoveryCodeRepository.findByEmail(email)).thenReturn(Optional.of(existingCode));

        userDetailsUseCase.sendRecoveryCode(email);

        verify(userRecoveryCodeRepository).save(userRecoveryCodeCaptor.capture());
        UserRecoveryCode updatedCode = userRecoveryCodeCaptor.getValue();
        Assertions.assertNotNull(updatedCode);
        Assertions.assertEquals(email, updatedCode.getEmail());
        Assertions.assertNotNull(updatedCode.getCode());
        Assertions.assertEquals(4, updatedCode.getCode().length());
        Assertions.assertNotEquals("1234", updatedCode.getCode());
        Assertions.assertNotNull(updatedCode.getCreatedAt());

        verify(mailIntegration).send(eq(email), anyString(), anyString());
    }

    @Test
    void given_NonExistentEmail_when_SendRecoveryCode_thenThrowsNotFoundException() {
        String email = "nonexistent@example.com";

        when(userRecoveryCodeRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(userDetailsRepository.findByUsername(email)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> userDetailsUseCase.sendRecoveryCode(email));

        verify(mailIntegration, never()).send(anyString(), anyString(), anyString());
        verify(userRecoveryCodeRepository, never()).save(any(UserRecoveryCode.class));
    }

    @Test
    void given_recoveryCodeIsValid_when_shouldUserIsFound_then_returnTrue() {
        ReflectionTestUtils.setField(userDetailsUseCase, "recoveryCodeTimeout", "5");
        when(userRecoveryCodeRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(getUserRecoveryCode()));
        Assertions.assertTrue(userDetailsUseCase.recoveryCodeIsValid("4444", user.getEmail()));

        verify(userRecoveryCodeRepository, times(1)).findByEmail(user.getEmail());
    }

    @Test
    void given_recoveryCodeIsValid_when_shouldUserIsNotFound_then_returnFalse() {
        ReflectionTestUtils.setField(userDetailsUseCase, "recoveryCodeTimeout", "5");
        when(userRecoveryCodeRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> userDetailsUseCase.recoveryCodeIsValid("4444", user.getEmail()));
    }

    private UserRecoveryCode getUserRecoveryCode() {
        return new UserRecoveryCode(UUID.randomUUID().toString(), user.getName(), "4444", LocalDateTime.now());
    }
}
