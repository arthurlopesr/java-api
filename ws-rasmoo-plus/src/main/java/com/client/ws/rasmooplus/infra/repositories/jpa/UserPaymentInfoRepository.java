package com.client.ws.rasmooplus.infra.repositories.jpa;

import com.client.ws.rasmooplus.domain.entities.jpa.UserPaymentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentInfoRepository extends JpaRepository<UserPaymentInfoEntity, Long> {
}
