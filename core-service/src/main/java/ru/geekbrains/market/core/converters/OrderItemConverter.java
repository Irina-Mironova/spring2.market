package ru.geekbrains.market.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.market.api.OrderItemDto;
import ru.geekbrains.market.core.entities.OrderItem;


@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
        orderItemDto.setProductTitle(orderItem.getProduct().getTitle());
        return orderItemDto;
    }
}
