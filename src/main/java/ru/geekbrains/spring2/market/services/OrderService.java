package ru.geekbrains.spring2.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring2.market.dtos.ShoppingCart;
import ru.geekbrains.spring2.market.entities.Order;
import ru.geekbrains.spring2.market.entities.OrderItem;
import ru.geekbrains.spring2.market.entities.User;
import ru.geekbrains.spring2.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring2.market.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final OrderRepository orderRepository;


    @Transactional
    public void createOrder(User user, String address, String phone) {
        Order order = new Order();
        ShoppingCart shoppingCart = shoppingCartService.getCurrentCart();
        order.setId(0L);
        order.setUser(user);
        order.setAddress(address);
        order.setPhone(phone);
        order.setTotalPrice(shoppingCart.getTotalPrice());
        order.setItems(shoppingCart.getItems().stream().map(item ->
                new OrderItem(null, productService.findById(item.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + item.getProductId() + " не найден")), order, item.getQuantity(),
                        item.getPricePerProduct(), item.getPrice())).collect(Collectors.toList()));
        orderRepository.save(order);
    }
}
