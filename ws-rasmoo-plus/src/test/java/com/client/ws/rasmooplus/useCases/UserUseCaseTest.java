package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.domain.entities.jpa.UserEntity;
import com.client.ws.rasmooplus.domain.entities.jpa.UserTypeEntity;
import com.client.ws.rasmooplus.domain.excepions.BadRequestException;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.infra.repositories.jpa.UserRepository;
import com.client.ws.rasmooplus.infra.repositories.jpa.UserTypeRepository;
import com.client.ws.rasmooplus.presentation.dto.UserDTO;
import com.client.ws.rasmooplus.useCases.impl.UserUseCaseImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserUseCaseImpl userUseCase;

    private UserDTO userDTO;

    private UserEntity user;

    private UserTypeEntity userType;

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
    }

    @Test
    void given_create_when_shouldIdIsNotNullAndUserTypeIsFound_then_returnUserCreated() {
        when(userTypeRepository.findById(1L)).thenReturn(Optional.of(userType));

        UserEntity user = new UserEntity();
        user.setDtSubscription(userDTO.getDtSubscription());
        user.setDtExpiration(userDTO.getDtExpiration());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setCpf(userDTO.getCpf());
        user.setUserType(userType);
        when(userRepository.save(user)).thenReturn(user);

        Assertions.assertEquals(user, userUseCase.create(userDTO));

        verify(userTypeRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void given_create_when_shouldIdIsNotNull_then_throwBadRequestException() {
        userDTO.setUserId(1L);

        Assertions.assertThrows(BadRequestException.class, () -> userUseCase.create(userDTO));

        verify(userTypeRepository, times(0)).findById(Mockito.any());
        verify(userRepository, times(0)).save(Mockito.any());
    }

    @Test
    void given_create_when_shouldIdIsNotNullAndUserTypeIsNotFound_then_throwNotFoundException() {
        when(userTypeRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> userUseCase.create(userDTO));

        verify(userTypeRepository, times(1)).findById(1L);
        verify(userRepository, times(0)).save(Mockito.any());
    }

    @Test
    void given_findAll_when_shouldReturnAllUsersCreated_returnUserList() {
        List<UserEntity> userList = new ArrayList<>();

        UserEntity user1 = new UserEntity();
        user1.setUserId(1L);
        user1.setName("User One");
        user1.setEmail("userone@test.com");
        user1.setCpf("12345678901");
        user1.setUserType(userType);

        UserEntity user2 = new UserEntity();
        user2.setUserId(2L);
        user2.setName("User Two");
        user2.setEmail("usertwo@test.com");
        user2.setCpf("12345678902");
        user2.setUserType(userType);

        userList.add(user1);
        userList.add(user2);

        when(userUseCase.findAll()).thenReturn(userList);
        List<UserEntity> results = userUseCase.findAll();
        Assertions.assertEquals(userList.size(), results.size());
        Assertions.assertTrue(results.containsAll(userList));
    }

    @Test
    void given_findById_when_shouldReturnUsersCreatedById_returnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserEntity result = userUseCase.findById(1L);
        Assertions.assertEquals(user, result);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void given_findById_when_shouldReturnNotFoundExceptionsWhenFindByIdThrows_returnNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> userUseCase.findById(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void given_update_when_shouldReturnUserUpdated_returnUserUpdated() {
        Long userId = 1L;
        Long userTypeId = 1L;
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setCpf(userDTO.getCpf());
        user.setUserType(userType);

        UserEntity updatedUser = new UserEntity();
        updatedUser.setUserId(userId);
        updatedUser.setName(userDTO.getName());
        updatedUser.setEmail(userDTO.getEmail());
        updatedUser.setCpf(userDTO.getCpf());
        updatedUser.setUserType(userType);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userTypeRepository.findById(userTypeId)).thenReturn(Optional.of(userType));
        when(userRepository.save(user)).thenReturn(updatedUser);

        UserEntity results = userUseCase.update(userId, userDTO);

        Assertions.assertEquals(updatedUser.getUserId(), results.getUserId());
        verify(userRepository, times(1)).findById(userId);
        verify(userTypeRepository, times(1)).findById(userTypeId);
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    void given_update_when_shouldReturnNotfoundExceptionWhenUpdateThrows_returnNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userTypeRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> userUseCase.update(1L, userDTO));

        verify(userTypeRepository, times(1)).findById(1L);
        verify(userRepository, times(0)).save(user);
    }

    @Test
    void given_delete_when_shouldReturnUserUpdated_returnUserDeleted() {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(user));
        userUseCase.delete(user.getUserId());
        verify(userRepository, times(1)).findById(user.getUserId());
        verify(userRepository, times(1)).deleteById(user.getUserId());
    }

    @Test
    void given_uploadPhoto_thereIsUserAndFileAndItsPNGorJPEG_then_updatePhotoAndReturnUser() throws Exception {
        FileInputStream fis = new FileInputStream("src/test/resources/static/test_image.webp");
        MockMultipartFile file = new MockMultipartFile("file", "test_image.webp", MediaType.MULTIPART_FORM_DATA_VALUE, fis);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserEntity userReturned = userUseCase.uploadPhoto(1L, file);
        Assertions.assertNotNull(userReturned);
        Assertions.assertNotNull(userReturned.getPhoto());
        Assertions.assertEquals("test_image.webp", userReturned.getPhotoName());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void given_uploadPhoto_thereIsUserAndFileAndItsNotPNGorJPEG_then_throwsBadRequestException() throws Exception {
        FileInputStream fis = new FileInputStream("src/test/resources/static/test_image.webp");
        MockMultipartFile file = new MockMultipartFile("file", "null_image.txt", MediaType.MULTIPART_FORM_DATA_VALUE, fis);

        Assertions.assertThrows(BadRequestException.class, () -> userUseCase.uploadPhoto(1L, file));

        verify(userRepository, times(0)).findById(any());
    }

    @Test
    void given_downloadPhoto_thereIsUserPhoto_then_returnByteArray() throws Exception {
        user.setPhoto(new byte[0]);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Assertions.assertNotNull(userUseCase.downloadPhoto(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void given_downloadPhoto_thereIsDontHavePhoto_then_returnBadRequestException() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Assertions.assertThrows(BadRequestException.class, () -> userUseCase.downloadPhoto(1L));
        verify(userRepository, times(1)).findById(1L);
    }
}
