package ru.ex.managerms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ex.managerms.client.ProductsRestClient;
import ru.ex.managerms.dto.UpdateProductDto;
import ru.ex.managerms.entity.Product;
import ru.ex.managerms.exceptions.custom.BadRequestException;
import ru.ex.managerms.exceptions.custom.ProductNotFound;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/products/{productId}")
public class ProductController {
    private final ProductsRestClient productsRestClient;
    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") UUID productId) {
        return productsRestClient.findProduct(productId)
                .orElseThrow(() -> new ProductNotFound("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public String getProduct() {
        return "catalogue/products/product";
    }

    @GetMapping("/edit")
    public String getProductEditPage() {
        return "catalogue/products/edit";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute(name = "product", binding = false) Product product,
                                UpdateProductDto payload,
                                Model model) {
        try {
            productsRestClient.updateProduct(product.id(), payload.title(), payload.details());
            return "redirect:/catalogue/products/%s".formatted(product.id());
        } catch (BadRequestException e) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", e.getErrors());
            return "catalogue/products/edit";
        }
    }

    @PostMapping("/delete")
    public String deleteProduct(@PathVariable("productId") UUID id) {
        productsRestClient.deleteProduct(id);
        return "redirect:/catalogue/products/list";
    }
}
