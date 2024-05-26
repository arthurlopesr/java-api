package com.client.ws.rasmooplus.infra.repositories.redis;

import com.client.ws.rasmooplus.domain.entities.redis.UserRecoveryCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRecoveryCodeRepository extends CrudRepository<UserRecoveryCode, String> {

    Optional<UserRecoveryCode> findByEmail(String email);
}
