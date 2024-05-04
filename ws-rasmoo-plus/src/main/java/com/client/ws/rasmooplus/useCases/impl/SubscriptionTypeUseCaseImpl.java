package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.SubscriptionsTypeEntity;
import com.client.ws.rasmooplus.domain.excepions.BadRequestException;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.dto.SubscriptionTypeDTO;
import com.client.ws.rasmooplus.factory.SubscriptionTypeFactory;
import com.client.ws.rasmooplus.infra.repositories.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.useCases.SubscriptionTypeUseCase;
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
    public List<SubscriptionsTypeEntity> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionsTypeEntity findById(Long id) {
        return getSubscriptionTypeById(id);
    }

    @Override
    public SubscriptionsTypeEntity create(SubscriptionTypeDTO subscriptionTypeDTO) {
        if (Objects.nonNull(subscriptionTypeDTO.getSubscriptionsTypeId())) {
            throw new BadRequestException("SubscriptionsTypeId most be null");
        }
        return subscriptionTypeRepository.save(SubscriptionTypeFactory.fromDtoToEntity(subscriptionTypeDTO));
    }

    @Override
    public SubscriptionsTypeEntity update(Long id, SubscriptionTypeDTO subscriptionTypeDTO) {
        getSubscriptionTypeById(id);
        subscriptionTypeDTO.setSubscriptionsTypeId(id);
        return subscriptionTypeRepository.save(SubscriptionTypeFactory.fromDtoToEntity(subscriptionTypeDTO));
    }

    @Override
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
