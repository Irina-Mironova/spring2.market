package ru.geekbrains.market.core.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.api.ProductFilter;
import ru.geekbrains.market.core.entities.Product;
import ru.geekbrains.market.core.repositories.ProductRepository;
import ru.geekbrains.market.core.repositories.specifications.ProductSpecification;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class ProductService {
    private final int SIZE_PAGE = 5;
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Page<Product> findAllWithFilters(Specification<Product> productSpecification, int page) {
        return productRepository.findAll(productSpecification, PageRequest.of(page, SIZE_PAGE));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Specification<Product> createSpecByFilters(ProductFilter productFilter) {
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
        return specification;
    }
}
