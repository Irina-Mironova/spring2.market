package ru.geekbrains.spring2.market.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring2.market.entities.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class ShoppingCart {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        this.productList = new ArrayList<>();
    }

    public void addProductToCart(Product product) {
        productList.add(product);
    }

    public void deleteProductFromCart(Product product) {
        productList.remove(product);
    }
}
