package com.client.ws.rasmooplus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableCaching
@OpenAPIDefinition(
        info = @Info(title = "API de criação de usuários para um sistema de transações financeiras",
                version = "1.0",
                description = "Esta API tem como função realizar todas as operações do sistema financeiro"))
public class WsRasmooPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsRasmooPlusApplication.class, args);
    }

}
