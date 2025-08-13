package ru.ex.cataloguems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ex.cataloguems.entity.Product;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Iterable<Product> findAllByTitleLikeIgnoreCase(String filter);
}
