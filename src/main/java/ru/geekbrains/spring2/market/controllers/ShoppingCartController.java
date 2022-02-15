package ru.geekbrains.spring2.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring2.market.entities.Product;
import ru.geekbrains.spring2.market.services.ShoppingCartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public List<Product> findAllInCart() {
        return shoppingCartService.findAllProductsInCart();
    }

    @GetMapping("/{productId}")
    public List<Product> addToCart(@PathVariable Long productId) {
        shoppingCartService.addProductToCart(productId);
        return shoppingCartService.findAllProductsInCart();
    }

    @DeleteMapping("/{productId}")
    public void deleteProductFromCart(@PathVariable Long productId) {
        shoppingCartService.deleteProductFromCart(productId);
    }
}
