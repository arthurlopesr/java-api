package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.domain.entities.SubscriptionsTypeEntity;
import com.client.ws.rasmooplus.dto.SubscriptionTypeDTO;

import java.util.List;

public interface SubscriptionTypeUseCase {

    List<SubscriptionsTypeEntity> findAll();

    SubscriptionsTypeEntity findById(Long id);

    SubscriptionsTypeEntity create(SubscriptionTypeDTO subscriptionsTypeDto);

    SubscriptionsTypeEntity update(Long id, SubscriptionTypeDTO subscriptionsTypeDto);

    void delete(Long id);

}
