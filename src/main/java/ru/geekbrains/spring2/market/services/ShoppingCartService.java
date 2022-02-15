package ru.geekbrains.spring2.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring2.market.entities.Product;
import ru.geekbrains.spring2.market.repositories.ProductRepository;
import ru.geekbrains.spring2.market.utils.ShoppingCart;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCart shoppingCart;
    private final ProductRepository productRepository;

    public void addProductToCart(Long productId){
        Product product = productRepository.findById(productId).get();
        shoppingCart.addProductToCart(product);
    }

    public void deleteProductFromCart(Long productId){
        Product product = productRepository.findById(productId).get();
        shoppingCart.deleteProductFromCart(product);
    }

    public List<Product> findAllProductsInCart() {
       return shoppingCart.getProductList();
    }
}
