package com.client.ws.rasmooplus.repositories;

import com.client.ws.rasmooplus.domain.entities.UserPaymentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentInfoRepository extends JpaRepository<UserPaymentInfoEntity, Long> {
}
