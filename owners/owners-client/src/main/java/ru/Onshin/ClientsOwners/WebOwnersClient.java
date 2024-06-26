package ru.Onshin.ClientsOwners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import ru.Onshin.owners.OwnerDto;

import java.util.List;

@Component
public class WebOwnersClient implements OwnersClient {
    private final WebClient webClient;
    private final KafkaTemplate<String, OwnerDto> ownerKafkaTemplate;

    @Autowired
    public WebOwnersClient(@Qualifier("ownerWebClient") WebClient webOwnersClient, @Qualifier("ownerKafkaTemplate")KafkaTemplate<String, OwnerDto> ownerKafkaTemplate) {
        this.webClient = webOwnersClient;
        this.ownerKafkaTemplate = ownerKafkaTemplate;
    }

    @Override
    public OwnerDto getOwnerById(int id) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/{id}");

        return webClient
                .get()
                .uri(uriBuilder.build().toUriString(), Integer.toString(id))
                .retrieve()
                .bodyToMono(OwnerDto.class)
                .block();
    }

    @Override
    public OwnerDto getOwnerByUserId(int id) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/user/{id}");

        return webClient
                .get()
                .uri(uriBuilder.build().toUriString(), Integer.toString(id))
                .retrieve()
                .bodyToMono(OwnerDto.class)
                .block();
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/");

        return webClient
                .get()
                .uri(uriBuilder.build().toUriString())
                .retrieve()
                .bodyToFlux(OwnerDto.class)
                .collectList()
                .block();
    }

    @Override
    public void createOwner(OwnerDto userDto) {
        ownerKafkaTemplate.send("topic-create-owner", userDto);
    }

    @Override
    public void updateOwner(OwnerDto ownerDto) {
        ownerKafkaTemplate.send("topic-update-owner", ownerDto);
    }

    @Override
    public void deleteOwner(int id) {
        var ownerDto = new OwnerDto();
        ownerDto.setId(id);

        ownerKafkaTemplate.send("topic-delete-owner", ownerDto);
    }
}
