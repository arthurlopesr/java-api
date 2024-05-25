package com.client.ws.rasmooplus.useCases.factory;

import com.client.ws.rasmooplus.domain.entities.jpa.SubscriptionsTypeEntity;
import com.client.ws.rasmooplus.presentation.dto.SubscriptionTypeDTO;

public class SubscriptionTypeFactory {
    private SubscriptionTypeFactory() {
    }

    public static SubscriptionsTypeEntity fromDtoToEntity(SubscriptionTypeDTO subscriptionTypeDTO) {
        return SubscriptionsTypeEntity.builder()
                .subscriptionsTypeId(subscriptionTypeDTO.getSubscriptionsTypeId())
                .name(subscriptionTypeDTO.getName())
                .accessMonths(subscriptionTypeDTO.getAccessMonths())
                .price(subscriptionTypeDTO.getPrice())
                .productKey(subscriptionTypeDTO.getProductKey())
                .build();
    }
}
