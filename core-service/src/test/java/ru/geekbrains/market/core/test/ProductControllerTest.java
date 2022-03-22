package ru.geekbrains.market.core.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.market.api.ProductDto;
import ru.geekbrains.market.core.entities.Product;
import ru.geekbrains.market.core.services.ProductService;

import java.math.BigDecimal;
import java.util.Optional;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void findById() throws Exception {
        Product product = new Product();
        product.setId(1212L);
        product.setTitle("Bread");
        product.setPrice(BigDecimal.valueOf(100));

        given(productService.findById(1212L)).willReturn(Optional.of(product));

        mockMvc
                .perform(
                        get("/api/v1/products/1212")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());



    }

}
