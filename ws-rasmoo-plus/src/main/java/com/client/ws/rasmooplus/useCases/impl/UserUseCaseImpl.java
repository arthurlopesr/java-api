package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.UserEntity;
import com.client.ws.rasmooplus.domain.entities.UserTypeEntity;
import com.client.ws.rasmooplus.domain.excepions.BadRequestException;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.infra.repositories.UserRepository;
import com.client.ws.rasmooplus.infra.repositories.UserTypeRepository;
import com.client.ws.rasmooplus.presentation.dto.UserDTO;
import com.client.ws.rasmooplus.useCases.UserUseCase;
import com.client.ws.rasmooplus.useCases.factory.UserFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserUseCaseImpl implements UserUseCase {

    private final UserRepository userRepository;

    private final UserTypeRepository userTypeRepository;

    UserUseCaseImpl(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    public UserEntity create(UserDTO userDTO) {
        if (Objects.nonNull((userDTO.getUserId()))) {
            throw new BadRequestException("User ID most be null");
        }

        var userTypeOpt = userTypeRepository.findById(userDTO.getUserTypeId());

        if (userTypeOpt.isEmpty()) {
            throw new NotFoundException("userTypeId not found");
        }

        UserTypeEntity userType = userTypeOpt.get();
        UserEntity user = UserFactory.fromDtoToEntity(userDTO, userType, null);
        return userRepository.save(user);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(Long id) {
        return getUserBYId(id);
    }

    public UserEntity update(Long id, UserDTO userDTO) {
        getUserBYId(id);
        userDTO.setUserId(id);
        var userTypeOpt = userTypeRepository.findById(userDTO.getUserTypeId());

        if (userTypeOpt.isEmpty()) {
            throw new NotFoundException("userTypeId not found");
        }

        UserTypeEntity userType = userTypeOpt.get();
        return userRepository.save(UserFactory.fromDtoToEntity(userDTO, userType, null));
    }

    private UserEntity getUserBYId(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (optionalUserEntity.isEmpty()) {
            throw new NotFoundException("UserEntity not found");
        }
        return optionalUserEntity.get();
    }
}
