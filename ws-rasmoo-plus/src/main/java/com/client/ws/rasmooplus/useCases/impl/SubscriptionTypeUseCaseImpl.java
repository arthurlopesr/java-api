package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.SubscriptionsTypeEntity;
import com.client.ws.rasmooplus.infra.repositories.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.useCases.SubscriptionTypeUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public SubscriptionsTypeEntity create(SubscriptionsTypeEntity subscriptionsType) {
        return null;
    }

    @Override
    public SubscriptionsTypeEntity update(Long id, SubscriptionsTypeEntity subscriptionsType) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
