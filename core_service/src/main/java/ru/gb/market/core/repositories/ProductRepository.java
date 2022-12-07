package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.core.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteById(Long id);
}
