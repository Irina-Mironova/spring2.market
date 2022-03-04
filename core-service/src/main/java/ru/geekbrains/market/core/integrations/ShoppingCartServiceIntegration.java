package ru.geekbrains.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.deekbrains.market.api.ShoppingCartDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShoppingCartServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${cart-service.url}")
    private String url;

    public Optional<ShoppingCartDto> getCurrentCart() {
        return Optional.ofNullable(restTemplate.getForObject(url, ShoppingCartDto.class));
    }

    public void clear() {
        restTemplate.getForObject(url + "/clear", ResponseEntity.class);
    }
}
