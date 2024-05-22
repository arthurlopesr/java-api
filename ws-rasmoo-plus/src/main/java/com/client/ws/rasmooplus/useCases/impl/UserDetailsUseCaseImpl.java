package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.UserCredentialsEntity;
import com.client.ws.rasmooplus.domain.excepions.BadRequestException;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.infra.repositories.UserDetailsRepository;
import com.client.ws.rasmooplus.useCases.UserDetailsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsUseCaseImpl implements UserDetailsUseCase {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserCredentialsEntity loadUserByUsernameAndPass(String username, String pass) {
        var userCredentialsOpt = userDetailsRepository.findByUsername(username);
        if (userCredentialsOpt.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        UserCredentialsEntity userCredentials = userCredentialsOpt.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(pass, userCredentials.getPassword())) {
            return userCredentials;
        }

        throw new BadRequestException("Invalid username or password");
    }
}
