package ru.geekbrains.market.core.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.market.core.entities.Product;
import ru.geekbrains.market.core.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void findByIdTest(){
        Product product = new Product();
        product.setTitle("Bread");
        product.setPrice(BigDecimal.valueOf(100));

        testEntityManager.persist(product);
        testEntityManager.flush();

        List<Product> productList = (List<Product>) productRepository.findAll();
        Assertions.assertEquals(9,productList.size());
        assertThat(productList).isNotNull();
        assertThat(productList).isNotEmpty();


    }


}
