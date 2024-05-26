package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.jpa.UserCredentialsEntity;
import com.client.ws.rasmooplus.domain.entities.redis.UserRecoveryCode;
import com.client.ws.rasmooplus.domain.excepions.BadRequestException;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.domain.utils.PasswordUtils;
import com.client.ws.rasmooplus.infra.gateways.integration.MailIntegration;
import com.client.ws.rasmooplus.infra.repositories.jpa.UserDetailsRepository;
import com.client.ws.rasmooplus.infra.repositories.redis.UserRecoveryCodeRepository;
import com.client.ws.rasmooplus.presentation.dto.UserDetailsDTO;
import com.client.ws.rasmooplus.useCases.UserDetailsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UserDetailsUseCaseImpl implements UserDetailsUseCase {

    @Value("${webservices.rasplus.redis.recoverycode.timeout}")
    private String recoveryCodeTimeout;

    private static final String userNotFound = "User not found!";

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRecoveryCodeRepository userRecoveryCodeRepository;

    @Autowired
    private MailIntegration mailIntegration;

    @Override
    public UserCredentialsEntity loadUserByUsernameAndPass(String username, String pass) {
        var userCredentialsOpt = getUserByUsername(username);
        UserCredentialsEntity userCredentials = userCredentialsOpt.get();

        return getUserCredentials(pass, userCredentials);
    }

    @Override
    public void sendRecoveryCode(String email) {
        String code = String.format("%04d", new Random().nextInt(10000));
        UserRecoveryCode userRecoveryCode;
        var userRecoveryCodeOpt = userRecoveryCodeRepository.findByEmail(email);

        if (userRecoveryCodeOpt.isEmpty()) {
            var userDetails = userDetailsRepository.findByUsername(email);

            if (userDetails.isEmpty()) {
                throw new NotFoundException(userNotFound);
            }

            userRecoveryCode = new UserRecoveryCode();
            userRecoveryCode.setEmail(email);
        } else {
            userRecoveryCode = userRecoveryCodeOpt.get();
        }
        userRecoveryCode.setCode(code);
        userRecoveryCode.setCreatedAt(LocalDateTime.now());
        userRecoveryCodeRepository.save(userRecoveryCode);
        mailIntegration.send(email, "Código de recuperação: "+code, "Código de reuperação de conta");
    }

    @Override
    public boolean recoveryCodeIsValid(String recoveryCode, String email) {
        var userRecoveryCodeOpt = userRecoveryCodeRepository.findByEmail(email);
        if (userRecoveryCodeOpt.isEmpty()) {
            throw new NotFoundException(userNotFound);
        }

        UserRecoveryCode userRecoveryCode = userRecoveryCodeOpt.get();
        LocalDateTime timeout = userRecoveryCode.getCreatedAt().plusMinutes(Long.parseLong(recoveryCodeTimeout));
        LocalDateTime now = LocalDateTime.now();

        return userRecoveryCode.getCode().equals(recoveryCode) && now.isBefore(timeout);
    }

    @Override
    public void updatePasswordByRecoveryCode(UserDetailsDTO userDetailsDTO) {
        if (recoveryCodeIsValid(userDetailsDTO.getRecoveryCode(), userDetailsDTO.getEmail())) {
            var userDetails = userDetailsRepository.findByUsername(userDetailsDTO.getEmail());
            UserCredentialsEntity userCredentials = userDetails.get();
            userCredentials.setPassword(PasswordUtils.encode(userDetailsDTO.getPassword()));
            userDetailsRepository.save(userCredentials);
        } else {
            throw new NotFoundException("");
        }
    }

    private Optional<UserCredentialsEntity> getUserByUsername(String username) {
        var userCredentialsOpt = userDetailsRepository.findByUsername(username);
        if (userCredentialsOpt.isEmpty()) {
            throw new NotFoundException(userNotFound);
        }
        return userCredentialsOpt;
    }

    private static UserCredentialsEntity getUserCredentials(String pass, UserCredentialsEntity userCredentials) {
        if (PasswordUtils.matches(pass, userCredentials.getPassword())) {
            return userCredentials;
        }
        throw new BadRequestException("Invalid username or password");
    }
}
