package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.SubscriptionsTypeEntity;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.dto.SubscriptionTypeDTO;
import com.client.ws.rasmooplus.infra.repositories.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.useCases.SubscriptionTypeUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<SubscriptionsTypeEntity> optionalSubscriptionsTypeEntity = subscriptionTypeRepository.findById(id);
        if(optionalSubscriptionsTypeEntity.isEmpty()) {
            throw new NotFoundException("SubscriptionType not found");
        }
        return subscriptionTypeRepository.findById(id).get();
    }

    @Override
    public SubscriptionsTypeEntity create(SubscriptionTypeDTO subscriptionTypeDTO) {
        return subscriptionTypeRepository.save(SubscriptionsTypeEntity.builder()
                        .subscriptionsTypeId(subscriptionTypeDTO.getSubscriptionsTypeId())
                        .name(subscriptionTypeDTO.getName())
                        .accessMonths(subscriptionTypeDTO.getAccessMonths())
                        .price(subscriptionTypeDTO.getPrice())
                        .productKey(subscriptionTypeDTO.getProductKey())
                .build());
    }

    @Override
    public SubscriptionsTypeEntity update(Long id, SubscriptionsTypeEntity subscriptionsType) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
