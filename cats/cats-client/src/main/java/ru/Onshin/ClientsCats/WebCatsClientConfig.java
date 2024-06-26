package ru.Onshin.ClientsCats;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebCatsClientConfig {
    @Value("${url.cats}")
    private String url;

    @Bean(value = "catWebClient")
    public WebClient webCatsClient() {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
