package com.client.ws.rasmooplus.presentation.controllers;

import com.client.ws.rasmooplus.presentation.dto.PaymentProcessDTO;
import com.client.ws.rasmooplus.useCases.impl.PaymentProcessInfoUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentInfoController {

    @Autowired
    private PaymentProcessInfoUseCaseImpl paymentProcessInfoUseCase;

    @PostMapping("/process")
    public ResponseEntity<Boolean> process(@RequestBody PaymentProcessDTO paymentProcessDTO) {
           return ResponseEntity.status(HttpStatus.OK).body(paymentProcessInfoUseCase.process(paymentProcessDTO));
    }
}
