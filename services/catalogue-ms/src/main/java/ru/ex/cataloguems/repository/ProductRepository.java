package ru.ex.cataloguems.repository;

import ru.ex.cataloguems.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(UUID productId);

    void deleteById(UUID id);
}
