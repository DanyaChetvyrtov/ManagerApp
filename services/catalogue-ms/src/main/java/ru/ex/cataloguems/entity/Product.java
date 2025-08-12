package ru.ex.cataloguems.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private UUID id;

    private String title;

    private String details;

    public Product(String title, String details) {
        this.title = title;
        this.details = details;
    }
}
