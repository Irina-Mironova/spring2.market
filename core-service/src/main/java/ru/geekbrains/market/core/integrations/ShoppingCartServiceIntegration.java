package ru.geekbrains.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.market.api.ShoppingCartDto;

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
