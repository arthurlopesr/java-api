package com.client.ws.rasmooplus.infra.repositories.jpa;

import com.client.ws.rasmooplus.domain.entities.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
