package ru.geekbrains.market.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.deekbrains.market.api.ProductDto;
import ru.deekbrains.market.api.ResourceNotFoundException;
import ru.geekbrains.market.carts.entities.ShoppingCart;
import ru.geekbrains.market.carts.integrations.ProductServiceIntegration;


import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ProductServiceIntegration productServiceIntegration;
    private ShoppingCart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new ShoppingCart();
    }

    public ShoppingCart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.add(product);
    }

    public void removeProduct(Long productId, int quantity) {
        tempCart.removeProduct(productId, quantity);
    }

    public void removeAll() {
        tempCart.removeAll();
    }

}
