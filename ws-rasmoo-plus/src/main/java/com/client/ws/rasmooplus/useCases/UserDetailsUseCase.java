package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.domain.entities.jpa.UserCredentialsEntity;

public interface UserDetailsUseCase {
    UserCredentialsEntity loadUserByUsernameAndPass(String username, String password);

    Object sendRecoveryCode(String email);
}
