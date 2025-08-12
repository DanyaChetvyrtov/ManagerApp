package ru.ex.managerms.repository;

import org.springframework.stereotype.Repository;
import ru.ex.managerms.entity.Product;

import java.util.*;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());


    @Override
    public List<Product> findAll() {

        return Collections.unmodifiableList(products);
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null)
            product.setId(UUID.randomUUID());

        products.add(product);
        return product;
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    @Override
    public void deleteById(UUID id) {
        products.removeIf(product -> product.getId().equals(id));
    }

}
