package ru.ex.managerms.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;
import ru.ex.managerms.client.ProductRestClientImpl;

@Configuration
public class ClientConfig {
    @Bean
    public ProductRestClientImpl productsRestClient(
            @Value("${services.catalogue.uri}") String catalogueBaseUri,
            @Value("${services.catalogue.username}") String catalogueBaseUsername,
            @Value("${services.catalogue.password}") String catalogueBasePassword
            ) {
        return new ProductRestClientImpl(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .requestInterceptor(
                        new BasicAuthenticationInterceptor(
                                catalogueBaseUsername,
                                catalogueBasePassword))
                .build());
    }

}
