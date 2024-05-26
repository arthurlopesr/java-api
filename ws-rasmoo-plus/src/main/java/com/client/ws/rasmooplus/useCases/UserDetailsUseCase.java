package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.domain.entities.jpa.UserCredentialsEntity;
import com.client.ws.rasmooplus.presentation.dto.UserDetailsDTO;

public interface UserDetailsUseCase {
    UserCredentialsEntity loadUserByUsernameAndPass(String username, String password);

    void sendRecoveryCode(String email);

    boolean recoveryCodeIsValid(String recoveryCode, String email);

    void updatePasswordByRecoveryCode(UserDetailsDTO userDetailsDTO);
}
