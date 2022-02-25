package ru.geekbrains.spring2.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private float pricePerProduct;
    private float price;

    public void changeQuantity(int delta) {
        quantity += delta;
        price = pricePerProduct * quantity;
    }
}
