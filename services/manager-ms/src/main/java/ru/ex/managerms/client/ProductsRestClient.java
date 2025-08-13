package ru.ex.managerms.client;

import ru.ex.managerms.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductsRestClient {

    List<Product> findAllProducts();

    Product createProduct(String title, String details);

    Optional<Product> findProduct(UUID productId);

    void updateProduct(UUID productId, String title, String details);

    void deleteProduct(UUID productId);

}
