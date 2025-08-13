package ru.ex.managerms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ex.managerms.client.ProductsRestClient;
import ru.ex.managerms.dto.NewProductDto;
import ru.ex.managerms.exceptions.custom.BadRequestException;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/catalogue/products")
public class ProductsController {
    private final ProductsRestClient productsRestClient;

    @GetMapping("/list")
    public String getProductsList(Model model, @RequestParam(name = "filter", required = false) String filter) {
        model.addAttribute("products", productsRestClient.findAllProducts(filter));
        model.addAttribute("filter", filter);
        return "catalogue/products/list";
    }


    @GetMapping("/create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("/create")
    public String createProduct(NewProductDto payload,
                                Model model) {
        try {
            var product = productsRestClient.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%s".formatted(product.id());
        } catch (BadRequestException e){
            model.addAttribute("payload", payload);
            model.addAttribute("errors", e.getErrors());
            return "catalogue/products/new_product";
        }
    }

}
