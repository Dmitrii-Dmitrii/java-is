package ru.Onshin.ClientsOwners;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebOwnersClientConfig {
    @Value("${url.owners}")
    private String url;

    @Bean(value = "ownerWebClient")
    public WebClient webOwnersClient() {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
