package ru.geekbrains.market.carts.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.market.carts.models.ShoppingCart;
import ru.geekbrains.market.carts.models.ShoppingCartItem;
import ru.geekbrains.market.carts.services.ShoppingCartService;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartService shoppingCartService;

    @Test
    public void getCurrentCurtTest() throws Exception {

        ShoppingCart shoppingCart = new ShoppingCart();

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setProductTitle("Bread");
        shoppingCartItem.setPricePerProduct(BigDecimal.valueOf(100));
        shoppingCartItem.setQuantity(2);
        shoppingCartItem.setPrice(BigDecimal.valueOf(200));
        shoppingCartItem.setProductId(1212L);

        shoppingCart.setTotalPrice(BigDecimal.valueOf(200));
        shoppingCart.setItems(List.of(shoppingCartItem));

        given(shoppingCartService.getCurrentCart("Bob")).willReturn(shoppingCart);

        mockMvc
                .perform(
                        get("/api/v1/cart")
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice", is(shoppingCart.getTotalPrice().intValue())));

    }
}
