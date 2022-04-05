package ru.geekbrains.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.ShoppingCartDto;
import ru.geekbrains.market.api.StringResponse;
import ru.geekbrains.market.carts.converters.ShoppingCartConverter;
import ru.geekbrains.market.carts.services.ShoppingCartService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartConverter shoppingCartConverter;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}")
    public ShoppingCartDto getCurrentCart(@RequestHeader(name = "username", required = false) String username,
                                          @PathVariable String uuid) {
        String targetUuid = getShoppingCartUuid(username, uuid);
        return shoppingCartConverter.entityToDto(shoppingCartService.getCurrentCart(targetUuid));
    }

    @GetMapping("/{uuid}/add/{id}")
    public void addToCart(@RequestHeader(name = "username", required = false) String username,
                          @PathVariable String uuid,
                          @PathVariable Long id) {
        String targetUuid = getShoppingCartUuid(username, uuid);
        shoppingCartService.add(id, 1, targetUuid);
    }

    @GetMapping("/{uuid}/remove")
    public void removeProduct(@RequestHeader(name = "username", required = false) String username,
                              @PathVariable String uuid,
                              @RequestParam Long productId,
                              @RequestParam(defaultValue = "0") int quantity) {
        String targetUuid = getShoppingCartUuid(username, uuid);
        shoppingCartService.removeProduct(productId, quantity, targetUuid);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCurrentCart(@RequestHeader(name = "username", required = false) String username,
                                 @PathVariable String uuid) {
        String targetUuid = getShoppingCartUuid(username, uuid);
        shoppingCartService.removeAll(targetUuid);
    }

    @GetMapping("/merge/{uuid_source}")
    public void mergerCarts(@RequestHeader(name = "username") String username, @PathVariable String uuid_source) {
        shoppingCartService.mergerCarts(username, uuid_source);
    }

    private String getShoppingCartUuid(String username, String uuid) {
        if (username != null) {
            return username;
        }
        return uuid;
    }
}
