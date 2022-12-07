package ru.gb.market.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.order.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUsername(String username);
}
