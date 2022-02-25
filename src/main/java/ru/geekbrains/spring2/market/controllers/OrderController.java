package ru.geekbrains.spring2.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring2.market.dtos.OrderData;
import ru.geekbrains.spring2.market.entities.User;
import ru.geekbrains.spring2.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring2.market.services.OrderService;
import ru.geekbrains.spring2.market.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderData orderData) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь с именем " + principal.getName() + " не найден"));
        orderService.createOrder(user, orderData.getAddress(), orderData.getPhone());
    }
}
