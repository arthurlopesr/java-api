package com.client.ws.rasmooplus.useCases;

import com.client.ws.rasmooplus.presentation.dto.PaymentProcessDTO;

public interface UserPaymentInfoUseCase {

    Boolean process(PaymentProcessDTO processDTO);
}
