package ru.ex.managerms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProductDto(
        @NotBlank(message = "{catalogue.products.update.errors.title_is_null}")
        @Size(min = 3, max = 50, message = "{catalogue.products.update.errors.title_size_is_invalid}")
        String title,
        @Size(max = 200, message = "{catalogue.products.update.errors.details_size_is_invalid}")
        String details
) {
}
