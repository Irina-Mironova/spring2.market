package ru.geekbrains.market.carts.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.market.api.ShoppingCartItemDto;
import ru.geekbrains.market.carts.models.ShoppingCartItem;

@Component
public class ShoppingCartItemConverter {

    public ShoppingCartItemDto entityToDto(ShoppingCartItem shoppingCartItem) {
        ShoppingCartItemDto shoppingCartItemDto = new ShoppingCartItemDto();
        shoppingCartItemDto.setProductId(shoppingCartItem.getProductId());
        shoppingCartItemDto.setProductTitle(shoppingCartItem.getProductTitle());
        shoppingCartItemDto.setPrice(shoppingCartItem.getPrice());
        shoppingCartItemDto.setQuantity(shoppingCartItem.getQuantity());
        shoppingCartItemDto.setPricePerProduct(shoppingCartItem.getPricePerProduct());
        return shoppingCartItemDto;

    }
}
