package ru.deekbrains.market.api;

import java.util.List;

public class ShoppingCartDto {
    private List<ShoppingCartItemDto> items;
    private float totalPrice;

    public List<ShoppingCartItemDto> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItemDto> items) {
        this.items = items;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
