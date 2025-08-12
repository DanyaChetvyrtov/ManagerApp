package ru.ex.managerms.exceptions.custom;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound() {
        super("Product not found");
    }

    public ProductNotFound(String message) {
        super(message);
    }
}
