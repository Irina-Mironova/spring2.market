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
    public ShoppingCartDto getCurrentCart(@RequestHeader(defaultValue = "noName") String username) {
        return shoppingCartConverter.entityToDto(shoppingCartService.getCurrentCart(username));
    }

    @GetMapping("/add/{id}")
    public void addToCart(@RequestHeader(defaultValue = "noName") String username, @PathVariable Long id) {
        shoppingCartService.add(id, username);
    }

    @GetMapping("/remove")
    public void removeProduct(@RequestHeader(defaultValue = "noName") String username,
                              @RequestParam Long productId,
                              @RequestParam(defaultValue = "0") int quantity) {
        shoppingCartService.removeProduct(productId, quantity, username);
    }

    @GetMapping("/clear")
    public void clearCurrentCart(@RequestHeader(defaultValue = "noName") String username) {
        shoppingCartService.removeAll(username);
    }
}
