package ru.geekbrains.market.carts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public void changeQuantity(int delta) {
        quantity += delta;
        price = BigDecimal.valueOf(pricePerProduct.doubleValue()*quantity).setScale(2, RoundingMode.HALF_UP);
    }
}
