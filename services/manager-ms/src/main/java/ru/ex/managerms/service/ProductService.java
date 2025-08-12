package ru.ex.managerms.service;

import ru.ex.managerms.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    List<Product> findAllProducts();

    Product createProduct(String title, String details);

    Optional<Product> findProduct(UUID productId);

    void updateProduct(UUID id, String title, String details);

    void deleteProduct(UUID id);

}
