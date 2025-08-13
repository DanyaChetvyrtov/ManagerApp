package ru.ex.cataloguems.service;

import ru.ex.cataloguems.entity.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Product createProduct(String title, String details);

    Optional<Product> findProduct(UUID productId);

    void updateProduct(UUID id, String title, String details);

    void deleteProduct(UUID id);

    Iterable<Product> findAllProducts(String filter);
}
