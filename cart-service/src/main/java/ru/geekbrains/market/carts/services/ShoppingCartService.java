package ru.geekbrains.market.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.api.ProductDto;
import ru.geekbrains.market.carts.models.ShoppingCart;
import ru.geekbrains.market.carts.integrations.ProductServiceIntegration;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ProductServiceIntegration productServiceIntegration;
    private HashMap<String, ShoppingCart> cartsList;

    @PostConstruct
    public void init() {
        cartsList = new HashMap<>();
    }

    public ShoppingCart getCurrentCart(String userName) {
        if (!cartsList.containsKey(userName)) {
            ShoppingCart shoppingCart = new ShoppingCart();
            cartsList.put(userName, shoppingCart);
            return shoppingCart;
        } else {
            ShoppingCart shoppingCart = cartsList.get(userName);
            return shoppingCart;
        }
    }

    public void add(Long productId, String userName) {
        ShoppingCart shoppingCart = getCurrentCart(userName);
        ProductDto product = productServiceIntegration.getProductById(productId);
        shoppingCart.add(product);
    }

    public void removeProduct(Long productId, int quantity, String userName) {
        ShoppingCart shoppingCart = getCurrentCart(userName);
        shoppingCart.removeProduct(productId, quantity);
    }

    public void removeAll(String userName) {
        ShoppingCart shoppingCart = getCurrentCart(userName);
        shoppingCart.removeAll();
    }

}
