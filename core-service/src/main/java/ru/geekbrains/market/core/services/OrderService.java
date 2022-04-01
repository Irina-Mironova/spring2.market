package ru.geekbrains.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.api.ResourceNotFoundException;
import ru.geekbrains.market.api.ShoppingCartDto;
import ru.geekbrains.market.core.entities.Order;
import ru.geekbrains.market.core.entities.OrderItem;
import ru.geekbrains.market.core.integrations.ShoppingCartServiceIntegration;
import ru.geekbrains.market.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ShoppingCartServiceIntegration shoppingCartServiceIntegration;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(String username, String address, String phone) {
        Order order = new Order();

        ShoppingCartDto shoppingCartDto = shoppingCartServiceIntegration.getCurrentCart(username);
        order.setUsername(username);
        order.setAddress(address);
        order.setPhone(phone);
        order.setTotalPrice(shoppingCartDto.getTotalPrice());
        order.setItems(shoppingCartDto.getItems().stream().map(item ->
                new OrderItem(
                        productService.findById(item.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + item.getProductId() + " не найден")),
                        order,
                        item.getQuantity(),
                        item.getPricePerProduct(),
                        item.getPrice()))
                .collect(Collectors.toList()));
        orderRepository.save(order);
        shoppingCartServiceIntegration.clear(username);
        return order;
    }
}
