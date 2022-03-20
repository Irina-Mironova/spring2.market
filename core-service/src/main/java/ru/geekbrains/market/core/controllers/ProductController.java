package ru.geekbrains.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.deekbrains.market.api.ProductDto;
import ru.deekbrains.market.api.ProductFilter;
import ru.deekbrains.market.api.ResourceNotFoundException;
import ru.geekbrains.market.core.converters.ProductConverter;
import ru.geekbrains.market.core.entities.Product;
import ru.geekbrains.market.core.repositories.specifications.ProductSpecification;
import ru.geekbrains.market.core.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;


    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll().stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    @PostMapping
    public List<ProductDto> findAllWithFilters(@RequestBody ProductFilter productFilter) {
        Specification<Product> specification = Specification.where(null);
        if (productFilter.getWord() != null) {
            specification = specification.and(ProductSpecification.titleContains(productFilter.getWord()));
        }
        if (productFilter.getMinPrice() != null) {
            specification = specification.and(ProductSpecification.priceGreaterThanOrEq(productFilter.getMinPrice()));
        }
        if (productFilter.getMaxPrice() != null) {
            specification = specification.and(ProductSpecification.priceLesserThanOrEq(productFilter.getMaxPrice()));
        }

        return productService.findAllWithFilters(specification).stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + id + " не найден"));
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
