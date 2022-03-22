package ru.geekbrains.market.carts.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.market.api.ShoppingCartDto;
import ru.geekbrains.market.carts.models.ShoppingCart;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShoppingCartConverter {
    private final ShoppingCartItemConverter shoppingCartItemConverter;

    public ShoppingCartDto entityToDto(ShoppingCart shoppingCart) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setTotalPrice(shoppingCart.getTotalPrice());
        shoppingCartDto.setItems(shoppingCart.getItems().stream().map(shoppingCartItemConverter::entityToDto).collect(Collectors.toList()));

        return shoppingCartDto;

    }
}
