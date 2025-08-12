package ru.ex.managerms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.ex.managerms.dto.UpdateProductDto;
import ru.ex.managerms.entity.Product;
import ru.ex.managerms.exceptions.custom.ProductNotFound;
import ru.ex.managerms.service.ProductService;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/products/{productId}")
public class ProductController {
    private final ProductService productService;
    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") UUID productId) {
        return this.productService.findProduct(productId)
                .orElseThrow(() -> new ProductNotFound("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public String getProductDetails(Model model) {
        return "catalogue/products/product";
    }

    @GetMapping("/edit")
    public String getProductEditPage(Model model) {
        return "catalogue/products/edit";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute(name = "product", binding = false) Product product,
                                @Valid UpdateProductDto payload,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalogue/products/edit";
        } else {
            productService.updateProduct(product.getId(), payload.title(), payload.details());
            return "redirect:/catalogue/products/%s".formatted(product.getId());
        }
    }

    @PostMapping("/delete")
    public String deleteProduct(@PathVariable("productId") UUID id) {
        productService.deleteProduct(id);
        return "redirect:/catalogue/products/list";
    }
}
