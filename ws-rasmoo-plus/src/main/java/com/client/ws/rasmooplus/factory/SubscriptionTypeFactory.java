package com.client.ws.rasmooplus.factory;

import com.client.ws.rasmooplus.domain.entities.SubscriptionsTypeEntity;
import com.client.ws.rasmooplus.dto.SubscriptionTypeDTO;

public class SubscriptionTypeFactory {

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
