package ru.geekbrains.market.carts.models;

import lombok.Data;
import ru.geekbrains.market.api.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Data
public class ShoppingCart {
    private List<ShoppingCartItem> items;
    private BigDecimal totalPrice;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void add(ProductDto product, int quantity) {
        for (ShoppingCartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(quantity);
                recalculate();
                return;
            }
        }
        items.add(new ShoppingCartItem(product.getId(), product.getTitle(), quantity, product.getPrice(),
                BigDecimal.valueOf(product.getPrice().doubleValue() * quantity).setScale(2, RoundingMode.HALF_UP)));
        recalculate();
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (ShoppingCartItem item : items) {
            totalPrice = totalPrice.add(item.getPrice()).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public void removeAll() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    public void removeProduct(Long productId, int quantity) {
        for (ShoppingCartItem item : items) {
            if (productId.equals(item.getProductId())) {
                item.changeQuantity(quantity);
                break;
            }
        }
        items.removeIf(item -> item.getQuantity() == 0);
        recalculate();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
