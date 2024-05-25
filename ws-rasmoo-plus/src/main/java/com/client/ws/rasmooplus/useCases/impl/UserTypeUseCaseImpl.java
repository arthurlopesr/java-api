package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.jpa.UserTypeEntity;
import com.client.ws.rasmooplus.domain.excepions.BadRequestException;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.infra.repositories.jpa.UserTypeRepository;
import com.client.ws.rasmooplus.presentation.dto.UserTypeDTO;
import com.client.ws.rasmooplus.useCases.UserTypeUseCase;
import com.client.ws.rasmooplus.useCases.factory.UserTypeFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserTypeUseCaseImpl implements UserTypeUseCase {

    private final UserTypeRepository userTypeRepository;

    UserTypeUseCaseImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public List<UserTypeEntity> findAll() {
        return userTypeRepository.findAll();
    }

    @Override
    public UserTypeEntity findById(Long id) {
        return getUserTypeEntityById(id);
    }

    @Override
    public UserTypeEntity create(UserTypeDTO userTypeDTO) {
        if (Objects.nonNull(userTypeDTO.getUserTypeId())) {
            throw new BadRequestException("UserTypeId most be null");
        }
        return userTypeRepository.save(UserTypeFactory.fromDtoToEntity(userTypeDTO));
    }

    @Override
    public UserTypeEntity update(Long id, UserTypeDTO userTypeDTO) {
        getUserTypeEntityById(id);
        userTypeDTO.setUserTypeId(id);
        return userTypeRepository.save(UserTypeFactory.fromDtoToEntity(userTypeDTO));
    }

    @Override
    public void delete(Long id) {
        getUserTypeEntityById(id);
        userTypeRepository.deleteById(id);
    }

    private UserTypeEntity getUserTypeEntityById(Long id) {
        Optional<UserTypeEntity> optionalUserTypeEntity = userTypeRepository.findById(id);
        if (optionalUserTypeEntity.isEmpty()) {
            throw new NotFoundException("UserTypeId not found");
        }
        return optionalUserTypeEntity.get();
    }
}
