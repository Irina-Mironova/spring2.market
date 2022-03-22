package ru.geekbrains.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.ShoppingCartDto;
import ru.geekbrains.market.carts.converters.ShoppingCartConverter;
import ru.geekbrains.market.carts.services.ShoppingCartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartConverter shoppingCartConverter;

    @GetMapping
    public ShoppingCartDto getCurrentCart() {
        return shoppingCartConverter.entityToDto(shoppingCartService.getCurrentCart());
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        shoppingCartService.add(id);
    }

    @GetMapping("/remove")
    public void removeProduct(@RequestParam Long productId, @RequestParam(defaultValue = "0") int quantity) {
        shoppingCartService.removeProduct(productId, quantity);
    }

    @GetMapping("/clear")
    public void clearCurrentCart() {
        shoppingCartService.removeAll();
    }
}
