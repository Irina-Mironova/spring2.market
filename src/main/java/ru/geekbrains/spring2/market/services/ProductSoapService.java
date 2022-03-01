package ru.geekbrains.spring2.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring2.market.entities.Product;
import ru.geekbrains.spring2.market.repositories.ProductRepository;
import ru.geekbrains.spring2.market.soap.ProductSoap;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSoapService {
    private final ProductRepository productRepository;

    public static Function<Product, ProductSoap> functionProductToSoap = product -> {
        ProductSoap productSoap = new ProductSoap();
        productSoap.setId(product.getId());
        productSoap.setTitle(product.getTitle());
        productSoap.setPrice(product.getPrice());
        return productSoap;
    };

    public List<ProductSoap> getAllProducts() {
        return productRepository.findAll().stream().map(functionProductToSoap).collect(Collectors.toList());
    }

    public ProductSoap getProductById(Long id) {
        return productRepository.findById(id).map(functionProductToSoap).get();
    }
}
