package ru.geekbrains.spring2.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring2.market.dtos.ShoppingCart;
import ru.geekbrains.spring2.market.services.ShoppingCartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ShoppingCart getCurrentCart() {
        return shoppingCartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        shoppingCartService.add(id);
    }

    @GetMapping("/deleteProduct")
    public void removeProduct(@RequestParam Long productId, @RequestParam(defaultValue = "0") int quantity) {
        shoppingCartService.removeProduct(productId, quantity);
    }

    @GetMapping("/delete")
    public void clearCurrentCart(){
        shoppingCartService.removeAll();
    }
}
