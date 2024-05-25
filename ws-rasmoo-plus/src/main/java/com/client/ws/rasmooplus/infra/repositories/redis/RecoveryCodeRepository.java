package com.client.ws.rasmooplus.infra.repositories.redis;

import com.client.ws.rasmooplus.domain.entities.redis.RecoveryCode;
import org.springframework.data.repository.CrudRepository;

public interface RecoveryCodeRepository extends CrudRepository<RecoveryCode, String> {
}
