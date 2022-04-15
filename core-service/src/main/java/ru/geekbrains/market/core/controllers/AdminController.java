package ru.geekbrains.market.core.controllers;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.StringResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public StringResponse admin(@RequestHeader String roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return new StringResponse("Пользователь является администратором");
        }
        return new StringResponse("Пользователь не является администратором");
    }

}
