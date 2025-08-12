package ru.ex.managerms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.ex.managerms.dto.NewProductDto;
import ru.ex.managerms.entity.Product;
import ru.ex.managerms.exceptions.custom.ProductNotFound;
import ru.ex.managerms.service.ProductService;

import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/catalogue/products")
public class ProductsController {
    private final ProductService productService;

    @GetMapping("/list")
    public String getProductsList(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("/create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("/create")
    public String createProduct(@Valid NewProductDto payload,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalogue/products/new_product";
        } else {
            var product = this.productService.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%s".formatted(product.getId());
        }
    }

}
