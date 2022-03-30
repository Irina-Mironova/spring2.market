package ru.geekbrains.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.ProductDto;
import ru.geekbrains.market.api.ProductFilter;
import ru.geekbrains.market.api.ResourceNotFoundException;
import ru.geekbrains.market.core.converters.ProductConverter;
import ru.geekbrains.market.core.entities.Product;
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
    public List<ProductDto> findAllWithFilters(@RequestBody ProductFilter productFilter,
                                               @RequestParam(defaultValue = "1", name = "page") Integer page) {

        if (page < 1) {
            page = 1;
        }
        Specification<Product> spec = productService.createSpecByFilters(productFilter);
        return productService.findAllWithFilters(spec, page - 1).stream().map(productConverter::entityToDto).collect(Collectors.toList());
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
