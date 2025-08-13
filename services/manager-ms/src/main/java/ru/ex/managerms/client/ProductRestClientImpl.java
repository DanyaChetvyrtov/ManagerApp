package ru.ex.managerms.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.ex.managerms.dto.NewProductDto;
import ru.ex.managerms.dto.UpdateProductDto;
import ru.ex.managerms.entity.Product;
import ru.ex.managerms.exceptions.custom.BadRequestException;
import ru.ex.managerms.exceptions.custom.ProductNotFound;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductRestClientImpl implements ProductsRestClient {

    private static final ParameterizedTypeReference<List<Product>> PRODUCTS_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    private final RestClient restClient;

    @Override
    public List<Product> findAllProducts() {
        return restClient
                .get()
                .uri("/catalogue-api/products")
                .retrieve().body(PRODUCTS_TYPE_REFERENCE);
    }

    @Override
    public Product createProduct(String title, String details) {
        try {
            return restClient
                    .post()
                    .uri("/catalogue-api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new NewProductDto(title, details))
                    .retrieve().body(Product.class);
        } catch (HttpClientErrorException.BadRequest e) {
            var problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public Optional<Product> findProduct(UUID productId) {
        try {
            return Optional.ofNullable(restClient
                    .get()
                    .uri("/catalogue-api/products/{productId}", productId)
                    .retrieve().body(Product.class));

        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateProduct(UUID productId, String title, String details) {
        try {
            restClient
                    .patch()
                    .uri("/catalogue-api/products/{productId}", productId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new UpdateProductDto(title, details))
                    .retrieve().toBodilessEntity();

        } catch (HttpClientErrorException.BadRequest e) {
            var problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));

        }

    }

    @Override
    public void deleteProduct(UUID productId) {
        try {
            restClient
                    .delete()
                    .uri("/catalogue-api/products/{productId}", productId)
                    .retrieve().toBodilessEntity();
        } catch (HttpClientErrorException.BadRequest e) {
            throw new ProductNotFound(e);
        }
    }
}
