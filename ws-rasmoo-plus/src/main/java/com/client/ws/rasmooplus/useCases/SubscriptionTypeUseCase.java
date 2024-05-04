package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.domain.entities.SubscriptionsTypeEntity;

import java.util.List;

public interface SubscriptionTypeUseCase {

    List<SubscriptionsTypeEntity> findAll();

    SubscriptionsTypeEntity findById(Long id);

    SubscriptionsTypeEntity create(SubscriptionsTypeEntity subscriptionsType);

    SubscriptionsTypeEntity update(Long id, SubscriptionsTypeEntity subscriptionsType);

    void delete(Long id);

}
