package ru.geekbrains.market.carts.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.market.api.ProductDto;
import ru.geekbrains.market.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.market.carts.services.ShoppingCartService;

import java.math.BigDecimal;


@SpringBootTest
public class ShoppingCartServiceTest {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @MockBean
    private ProductServiceIntegration productServiceIntegration;

    @Test
    public void addTest() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1212L);
        productDto.setTitle("Bread");
        productDto.setPrice(BigDecimal.valueOf(100));

        Mockito.doReturn(productDto).when(productServiceIntegration).getProductById(1212L);
        shoppingCartService.add(1212L, "Bob");

        Assertions.assertEquals(shoppingCartService.getCurrentCart("Bob").getTotalPrice().intValue(), 100);

    }
}
