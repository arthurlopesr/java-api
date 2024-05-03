package com.client.ws.rasmooplus.repositories;

import com.client.ws.rasmooplus.domain.entities.SubscriptionsTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionsTypeEntity, Long> {
}
