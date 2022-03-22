package ru.geekbrains.market.core.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.market.api.ShoppingCartDto;
import ru.geekbrains.market.api.ShoppingCartItemDto;
import ru.geekbrains.market.core.entities.Order;
import ru.geekbrains.market.core.entities.Product;
import ru.geekbrains.market.core.integrations.ShoppingCartServiceIntegration;
import ru.geekbrains.market.core.repositories.OrderRepository;
import ru.geekbrains.market.core.services.OrderService;
import ru.geekbrains.market.core.services.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    private ShoppingCartServiceIntegration shoppingCartServiceIntegration;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderRepository orderRepository;


    @Test
    public void createOrderTest(){
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        List<ShoppingCartItemDto> items = new ArrayList<>();
        ShoppingCartItemDto shoppingCartItemDto = new ShoppingCartItemDto();
        shoppingCartItemDto.setProductTitle("Bread");
        shoppingCartItemDto.setPricePerProduct(BigDecimal.valueOf(100));
        shoppingCartItemDto.setQuantity(2);
        shoppingCartItemDto.setPrice(BigDecimal.valueOf(200));
        shoppingCartItemDto.setProductId(1212L);
        shoppingCartDto.setTotalPrice(BigDecimal.valueOf(200));
        shoppingCartDto.setItems(List.of(shoppingCartItemDto));

        Product product = new Product();
        product.setId(1212L);
        product.setTitle("Bread");
        product.setPrice(BigDecimal.valueOf(100));

        Mockito.doReturn(shoppingCartDto).when(shoppingCartServiceIntegration).getCurrentCart();
        Mockito.doReturn(Optional.of(product)).when(productService).findById(1212L);


        Order order = orderService.createOrder("bob","Москва","89174241435");
        Assertions.assertEquals(order.getTotalPrice(),BigDecimal.valueOf(200));
        Mockito.verify(orderRepository,Mockito.times(1)).save(ArgumentMatchers.any());
    }
}
