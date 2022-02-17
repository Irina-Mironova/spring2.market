package ru.geekbrains.spring2.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring2.market.dtos.ProductDto;
import ru.geekbrains.spring2.market.entities.Product;
import ru.geekbrains.spring2.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring2.market.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll().stream().map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + id + " не найден"));
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
