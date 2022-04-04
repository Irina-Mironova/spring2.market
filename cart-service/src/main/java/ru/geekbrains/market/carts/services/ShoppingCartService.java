package ru.geekbrains.market.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.api.ProductDto;
import ru.geekbrains.market.carts.models.ShoppingCart;
import ru.geekbrains.market.carts.integrations.ProductServiceIntegration;


import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    public ShoppingCart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!redisTemplate.hasKey(targetUuid)) {
            redisTemplate.opsForValue().set(targetUuid, new ShoppingCart());
        }
        return (ShoppingCart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void add(Long productId, String uuid) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        execute(uuid, shoppingCart -> shoppingCart.add(product));
    }

    public void removeProduct(Long productId, int quantity, String uuid) {
        execute(uuid, shoppingCart -> shoppingCart.removeProduct(productId, quantity));
    }

    public void removeAll(String uuid) {
        execute(uuid, ShoppingCart::removeAll);
    }

    private void execute(String uuid, Consumer<ShoppingCart> operation) {
        ShoppingCart shoppingCart = getCurrentCart(uuid);
        operation.accept(shoppingCart);
        redisTemplate.opsForValue().set(cartPrefix + uuid, shoppingCart);
    }

}
