package com.client.ws.rasmooplus.infra.repositories;

import com.client.ws.rasmooplus.domain.entities.UserCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserCredentialsEntity, Long> {
    Optional<UserCredentialsEntity> findByUsername(String username);
}
