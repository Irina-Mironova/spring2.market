package ru.geekbrains.market.core.converters;

import org.springframework.stereotype.Component;
import ru.deekbrains.market.api.ProductDto;
import ru.geekbrains.market.core.entities.Product;

@Component
public class ProductConverter {
    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

}
