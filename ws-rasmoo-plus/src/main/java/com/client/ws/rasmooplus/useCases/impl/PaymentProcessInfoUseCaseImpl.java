package com.client.ws.rasmooplus.useCases.impl;

import com.client.ws.rasmooplus.domain.entities.UserEntity;
import com.client.ws.rasmooplus.domain.entities.UserPaymentInfoEntity;
import com.client.ws.rasmooplus.domain.excepions.BusinessException;
import com.client.ws.rasmooplus.domain.excepions.NotFoundException;
import com.client.ws.rasmooplus.infra.gateways.dto.CostumerDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.OrderDTO;
import com.client.ws.rasmooplus.infra.gateways.dto.PaymentDTO;
import com.client.ws.rasmooplus.infra.gateways.factory.CreditCardFactory;
import com.client.ws.rasmooplus.infra.gateways.factory.CustomerFactory;
import com.client.ws.rasmooplus.infra.gateways.factory.OrderFactory;
import com.client.ws.rasmooplus.infra.gateways.factory.PaymentFactory;
import com.client.ws.rasmooplus.infra.gateways.integration.MailIntegration;
import com.client.ws.rasmooplus.infra.gateways.integration.WsRaspayIntegration;
import com.client.ws.rasmooplus.infra.repositories.UserPaymentInfoRepository;
import com.client.ws.rasmooplus.infra.repositories.UserRepository;
import com.client.ws.rasmooplus.presentation.dto.PaymentProcessDTO;
import com.client.ws.rasmooplus.useCases.UserPaymentInfoUseCase;
import com.client.ws.rasmooplus.useCases.factory.UserPaymentInfoFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaymentProcessInfoUseCaseImpl implements UserPaymentInfoUseCase {

    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;
    private final WsRaspayIntegration wsRaspayIntegration;
    private final MailIntegration mailIntegration;

    PaymentProcessInfoUseCaseImpl(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository, WsRaspayIntegration wsRaspayIntegration, MailIntegration mailIntegration) {
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
        this.wsRaspayIntegration = wsRaspayIntegration;
        this.mailIntegration = mailIntegration;
    }

    @Override
    public Boolean process(PaymentProcessDTO processDTO) {
        var userOpt = userRepository.findById(processDTO.getUserPaymentInfoDTO().getUserId());
        if (userOpt.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        UserEntity user = userOpt.get();
        if (Objects.nonNull(user.getSubscriptionsType())) {
            throw new BusinessException("Payment not processed because the user already has a subscription");
        }
        CostumerDTO createRaspayUser = wsRaspayIntegration.createCostumer(CustomerFactory.factoryCostumer(user));
        OrderDTO createPaymentOrder = wsRaspayIntegration.createOrder(OrderFactory.orderFactoy(createRaspayUser.getId(), processDTO));
        PaymentDTO processPayment = PaymentFactory.build(createRaspayUser.getId(), createPaymentOrder.getId(), CreditCardFactory.build(processDTO.getUserPaymentInfoDTO(), user.getCpf()));
        Boolean successPayment =  wsRaspayIntegration.processPayment(processPayment);

        if (successPayment) {
            UserPaymentInfoEntity userPaymentInfoInstance = UserPaymentInfoFactory.fromDtoToEntity(processDTO.getUserPaymentInfoDTO(), user);
            userPaymentInfoRepository.save(userPaymentInfoInstance);
            mailIntegration.send(user.getEmail(), "User: "+ user.getEmail()+"- Senha: teste", "Acesso Liberado");
        }
        //enviar email de criação de conta
        //retornar sucesso ou falha

        return null;
    }
}
