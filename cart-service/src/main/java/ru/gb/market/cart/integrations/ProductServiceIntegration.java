package ru.gb.market.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.market.api.core.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;
    @Value("${integration.core-service.url}")
    private String productServiceUrl;

    public Optional<ProductDto> findById(Long id){
        ProductDto productDto = restTemplate.getForObject(productServiceUrl + "/api/v1/products/" + id, ProductDto.class);//получаем продукт из сервиса "core"
        return Optional.ofNullable(productDto);//делаем Optional для того что бы в дальнейшем сделать проверку и если ошибка кинуть глобально исключение
    }
}
