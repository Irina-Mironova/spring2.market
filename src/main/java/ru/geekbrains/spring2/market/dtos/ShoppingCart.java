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
        items.add(new ShoppingCartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (ShoppingCartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    private ShoppingCartItem findOrderFromProduct(Product product) {
        return items.stream().filter(o -> o.getProductId().equals(product.getId())).findFirst().orElse(null);
    }

    public void remove(Product product) {
        ShoppingCartItem item = findOrderFromProduct(product);
        if (item == null) {
            return;
        }
        items.remove(item);
        recalculate();
    }
}
