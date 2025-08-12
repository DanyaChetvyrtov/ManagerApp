package ru.ex.managerms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewProductDto(
        @NotBlank(message = "{catalogue.products.create.errors.title_is_null}")
        @Size(min = 3, max = 50, message = "{catalogue.products.create.errors.title_size_is_invalid}")
        String title,
        @Size(max = 200, message = "{catalogue.products.create.errors.details_size_is_invalid}")
        String details
) {
}
