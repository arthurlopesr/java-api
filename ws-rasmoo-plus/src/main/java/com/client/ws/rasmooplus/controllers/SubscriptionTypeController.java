package com.client.ws.rasmooplus.controllers;

import com.client.ws.rasmooplus.domain.entities.SubscriptionsTypeEntity;
import com.client.ws.rasmooplus.useCases.SubscriptionTypeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscription-type")
public class SubscriptionTypeController {

    @Autowired
    private SubscriptionTypeUseCase subscriptionTypeUseCase;

    @GetMapping()
    public ResponseEntity<List<SubscriptionsTypeEntity>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeUseCase.findAll());
    }
}
