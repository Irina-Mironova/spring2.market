package ru.geekbrains.spring2.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring2.market.entities.Product;
import ru.geekbrains.spring2.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring2.market.dtos.ShoppingCart;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ProductService productService;
    private ShoppingCart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new ShoppingCart();
    }

    public ShoppingCart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Не удается добавить продукт с id: " + productId + " в корзину. Продукт не найден"));
        tempCart.add(product);
    }

    public void removeProduct(Long productId, int quantity) {
        tempCart.removeProduct(productId, quantity);
    }

    public void removeAll() {
        tempCart.removeAll();
    }

}
