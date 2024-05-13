package com.client.ws.rasmooplus.infra.gateways.integration;

public interface MailIntegration {
    void send(String mailTo, String message, String subject);
}
