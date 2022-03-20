package ru.geekbrains.market.core.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.core.entities.Product;
import ru.geekbrains.market.core.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public List<Product> findAllWithFilters(Specification<Product> productSpecification) {
        return productRepository.findAll(productSpecification);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
