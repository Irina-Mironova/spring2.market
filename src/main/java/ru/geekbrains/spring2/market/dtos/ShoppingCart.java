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
        ShoppingCartItem item = findProductInCart(product);
        if (item == null) {
            items.add(new ShoppingCartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        } else {
            item.setQuantity(item.getQuantity() +  1);
            item.setPrice(item.getQuantity() * item.getPricePerProduct());
        }
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (ShoppingCartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    private ShoppingCartItem findProductInCart(Product product) {
        return items.stream().filter(o -> o.getProductId().equals(product.getId())).findFirst().orElse(null);
    }

    public void removeAll() {
        items.clear();
        recalculate();
    }

    public void removeProduct(Product product, int quantity){
        ShoppingCartItem item = findProductInCart(product);
        if (item == null) {
            return;
        }
        if (quantity == 0) {
            items.remove(item);
            recalculate();
            return;
        }

        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            item.setPrice(item.getQuantity() * item.getPricePerProduct());
        } else{
            items.remove(item);
        }
        recalculate();
    }
}
