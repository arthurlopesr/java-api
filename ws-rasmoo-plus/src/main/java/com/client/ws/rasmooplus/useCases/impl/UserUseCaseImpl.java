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

import java.util.Objects;

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
}
