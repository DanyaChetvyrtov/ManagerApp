package ru.ex.managerms.repository;

import ru.ex.managerms.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(UUID productId);

    void deleteById(UUID id);
}
