package ru.gb.market.cart.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CartAppConfig {
    @Bean
    public RestTemplate restTemplate(){//создаем бин для общения между микросервисами
        return new RestTemplate();
    }
}
