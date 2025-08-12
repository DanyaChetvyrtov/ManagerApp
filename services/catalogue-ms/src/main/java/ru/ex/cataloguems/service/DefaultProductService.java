package ru.ex.cataloguems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ex.cataloguems.entity.Product;
import ru.ex.cataloguems.exception.custom.ProductNotFound;
import ru.ex.cataloguems.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String details) {
        return productRepository.save(new Product(title, details));
    }

    @Override
    public Optional<Product> findProduct(UUID productId) {
        return productRepository.findById(productId);
    }

    @Override
    public void updateProduct(UUID id, String title, String details) {
        productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setDetails(details);
                }, ProductNotFound::new);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
