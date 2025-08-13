package ru.ex.managerms.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import ru.ex.managerms.client.ProductRestClientImpl;

@Configuration
public class ClientConfig {
    @Bean
    public ProductRestClientImpl productsRestClient(
            @Value("${services.catalogue.uri}") String catalogueBaseUri) {
        return new ProductRestClientImpl(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .build());
    }

}
