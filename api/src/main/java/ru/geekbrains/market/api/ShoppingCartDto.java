package ru.geekbrains.market.api;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartDto {
    private List<ShoppingCartItemDto> items;
    private BigDecimal totalPrice;

    public List<ShoppingCartItemDto> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
