package ru.geekbrains.market.auth.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ShoppingCartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public void mergerCarts(String username, String uuid_source) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/merge/" + uuid_source)
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}