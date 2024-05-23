package com.client.ws.rasmooplus.infra.repositories;

import com.client.ws.rasmooplus.domain.entities.SubscriptionsTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionsTypeEntity, Long> {
    Optional<SubscriptionsTypeEntity> findByProductKey(String productKey);
}
