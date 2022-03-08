package ru.geekbrains.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.deekbrains.market.api.ProductDto;
import ru.deekbrains.market.api.ResourceNotFoundException;
import ru.deekbrains.market.api.ShoppingCartDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShoppingCartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public ShoppingCartDto getCurrentCart() {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart")
                .retrieve()
                .bodyToMono(ShoppingCartDto.class)
                .block();
    }

    public void clear() {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/clear")
                .retrieve()
                .toBodilessEntity()
                .block();

    }
}
