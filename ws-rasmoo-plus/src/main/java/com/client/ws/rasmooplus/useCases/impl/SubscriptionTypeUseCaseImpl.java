package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.jpa.SubscriptionsTypeEntity;
import com.client.ws.rasmooplus.domain.excepions.BadRequestException;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.presentation.dto.SubscriptionTypeDTO;
import com.client.ws.rasmooplus.useCases.factory.SubscriptionTypeFactory;
import com.client.ws.rasmooplus.infra.repositories.jpa.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.useCases.SubscriptionTypeUseCase;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeUseCaseImpl implements SubscriptionTypeUseCase {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    SubscriptionTypeUseCaseImpl(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    @Cacheable(value = "subscriptionType")
    public List<SubscriptionsTypeEntity> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    @Cacheable(value = "subscriptionType")
    public SubscriptionsTypeEntity findById(Long id) {
        return getSubscriptionTypeById(id);
    }

    @Override
    @CacheEvict(value = "subscriptionType", allEntries = true)
    public SubscriptionsTypeEntity create(SubscriptionTypeDTO subscriptionTypeDTO) {
        if (Objects.nonNull(subscriptionTypeDTO.getSubscriptionsTypeId())) {
            throw new BadRequestException("SubscriptionsTypeId most be null");
        }
        return subscriptionTypeRepository.save(SubscriptionTypeFactory.fromDtoToEntity(subscriptionTypeDTO));
    }

    @Override
    @CacheEvict(value = "subscriptionType", allEntries = true)
    public SubscriptionsTypeEntity update(@Valid Long id, SubscriptionTypeDTO subscriptionTypeDTO) {
        getSubscriptionTypeById(id);
        subscriptionTypeDTO.setSubscriptionsTypeId(id);
        return subscriptionTypeRepository.save(SubscriptionTypeFactory.fromDtoToEntity(subscriptionTypeDTO));
    }

    @Override
    @CacheEvict(value = "subscriptionType", allEntries = true)
    public void delete(Long id) {
        getSubscriptionTypeById(id);
        subscriptionTypeRepository.deleteById(id);
    }

    private SubscriptionsTypeEntity getSubscriptionTypeById(Long id) {
        Optional<SubscriptionsTypeEntity> optionalSubscriptionsTypeEntity = subscriptionTypeRepository.findById(id);
        if (optionalSubscriptionsTypeEntity.isEmpty()) {
            throw new NotFoundException("SubscriptionType not found");
        }
        return optionalSubscriptionsTypeEntity.get();
    }
}
