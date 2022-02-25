package ru.geekbrains.spring2.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring2.market.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
