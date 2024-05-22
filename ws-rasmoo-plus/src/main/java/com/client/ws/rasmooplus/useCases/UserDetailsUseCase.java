package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.domain.entities.UserCredentialsEntity;

public interface UserDetailsUseCase {
    UserCredentialsEntity loadUserByUsernameAndPass(String username, String password);
}
