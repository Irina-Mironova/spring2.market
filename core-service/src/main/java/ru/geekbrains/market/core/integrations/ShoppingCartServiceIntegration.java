package ru.geekbrains.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.market.api.ShoppingCartDto;

@Component
@RequiredArgsConstructor
public class ShoppingCartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public ShoppingCartDto getCurrentCart(String username) {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart")
                .header("username", username)
                .retrieve()
                .bodyToMono(ShoppingCartDto.class)
                .block();
    }

    public void clear(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();

    }
}
