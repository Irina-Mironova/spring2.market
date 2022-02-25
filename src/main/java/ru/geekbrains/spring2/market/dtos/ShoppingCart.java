package ru.geekbrains.spring2.market.dtos;

import lombok.Data;
import ru.geekbrains.spring2.market.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
public class ShoppingCart {
    private List<ShoppingCartItem> items;
    private float totalPrice;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public List<ShoppingCartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) {
        for (ShoppingCartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new ShoppingCartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (ShoppingCartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void removeAll() {
        items.clear();
        recalculate();
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
}
