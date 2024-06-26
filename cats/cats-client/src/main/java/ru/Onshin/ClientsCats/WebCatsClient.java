package ru.Onshin.ClientsCats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import ru.Onshin.cats.CatDto;
import ru.Onshin.cats.Colors;
import ru.Onshin.cats.UserCatDto;

import java.util.List;

@Component
public class WebCatsClient implements CatsClient{
    private final WebClient webClient;
    private final KafkaTemplate<String, UserCatDto> catKafkaTemplate;

    @Autowired
    public WebCatsClient(@Qualifier("catWebClient") WebClient webCatsClient, @Qualifier("catKafkaTemplate")KafkaTemplate<String, UserCatDto> catKafkaTemplate) {
        this.webClient = webCatsClient;
        this.catKafkaTemplate = catKafkaTemplate;
    }

    @Override
    public CatDto getCatById(int id, String currentOwnerName) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/{id}")
                .queryParam("currentOwnerName", currentOwnerName);

        return webClient
                .get()
                .uri(uriBuilder.build().toUriString(), id)
                .retrieve()
                .bodyToMono(CatDto.class)
                .block();
    }

    @Override
    public List<CatDto> getCats(String currentOwnerName, String name, String breed, Colors color) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/");

        if (currentOwnerName != null && !currentOwnerName.isEmpty()) {
            uriBuilder.queryParam("currentOwnerName", currentOwnerName);
        }
        if (name != null && !name.isEmpty()) {
            uriBuilder.queryParam("name", name);
        }
        if (color != null) {
            uriBuilder.queryParam("color", color);
        }
        if (breed != null && !breed.isEmpty()) {
            uriBuilder.queryParam("breed", breed);
        }

        return webClient
                .get()
                .uri(uriBuilder.build().toUriString())
                .retrieve()
                .bodyToFlux(CatDto.class)
                .collectList()
                .block();
    }

    @Override
    public void createCat(CatDto catDto) {
        var userCatDto = new UserCatDto();
        userCatDto.setCatDto(catDto);

        catKafkaTemplate.send("topic-create-cat", userCatDto);
    }

    @Override
    public void updateCat(CatDto catDto) {
        var userCatDto = new UserCatDto();
        userCatDto.setCatDto(catDto);

        catKafkaTemplate.send("topic-update-cat", userCatDto);
    }

    @Override
    public void deleteCat(int id, String currentOwnerName) {
        var catDto = new CatDto();
        catDto.setId(id);

        var userCatDto = new UserCatDto();
        userCatDto.setCatDto(catDto);
        userCatDto.setCurrentOwnerName(currentOwnerName);

        catKafkaTemplate.send("topic-delete-cat", userCatDto);
    }

    @Override
    public CatDto adminGetCatById(int id) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/admin/{id}");

        return webClient
                .get()
                .uri(uriBuilder.build().toUriString(), Integer.toString(id))
                .retrieve()
                .bodyToMono(CatDto.class)
                .block();
    }

    @Override
    public List<CatDto> adminGetCats(String name, String breed, Colors color) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/admin/");

        if (name != null && !name.isEmpty()) {
            uriBuilder.queryParam("name", name);
        }
        if (color != null) {
            uriBuilder.queryParam("color", color);
        }
        if (breed != null && !breed.isEmpty()) {
            uriBuilder.queryParam("breed", breed);
        }

        return webClient
                .get()
                .uri(uriBuilder.build().toUriString())
                .retrieve()
                .bodyToFlux(CatDto.class)
                .collectList()
                .block();
    }

    @Override
    public void adminDeleteCat(int id) {
        var catDto = new CatDto();
        catDto.setId(id);

        var userCatDto = new UserCatDto();
        userCatDto.setCatDto(catDto);

        catKafkaTemplate.send("topic-admin-delete-cat", userCatDto);
    }
}
