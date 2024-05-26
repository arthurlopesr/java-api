package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.jpa.UserCredentialsEntity;
import com.client.ws.rasmooplus.domain.entities.redis.UserRecoveryCode;
import com.client.ws.rasmooplus.domain.excepions.BadRequestException;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.infra.repositories.jpa.UserDetailsRepository;
import com.client.ws.rasmooplus.infra.repositories.redis.UserRecoveryCodeRepository;
import com.client.ws.rasmooplus.useCases.UserDetailsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserDetailsUseCaseImpl implements UserDetailsUseCase {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRecoveryCodeRepository userRecoveryCodeRepository;

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

    @Override
    public Object sendRecoveryCode(String email) {
        String code = String.format("%04d", new Random().nextInt(10000));

        userRecoveryCodeRepository.save(UserRecoveryCode.builder().code(code).build());
        return null;
    }
}
