package ru.Onshin.Сats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.Onshin.Cats.CatDto;
import ru.Onshin.Cats.CatService;
import ru.Onshin.Cats.Colors;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/{id}")
    public CatDto getCatById(@PathVariable int id) {
        return catService.findCatByIdAndOwnerName(id);
    }

    @GetMapping("/")
    public List<CatDto> getAllCats() {
        return catService.findAllCatsByOwnerName();
    }

    @GetMapping("/name/{name}")
    public CatDto getCatByName(@PathVariable String name) {
        return catService.findCatByNameAndOwnerName(name);
    }

    @GetMapping("/breed/{breed}")
    public List<CatDto> getCatsByBreed(@PathVariable String breed) {
        return catService.findCatByBreedAndOwnerName(breed);
    }

    @GetMapping("/color/{color}")
    public List<CatDto> getCatsByColor(@PathVariable Colors color) {
        return catService.findCatByColorAndOwnerName(color);
    }

    @PostMapping("/")
    public CatDto createCat(@RequestBody CatDto catDto) {
        return catService.createCat(catDto);
    }

    @PutMapping("/")
    public CatDto updateCat(@RequestBody CatDto catDto) {
        return catService.updateCat(catDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCat(@PathVariable int id) {
        return catService.deleteCatByIdAndOwnerName(id);
    }
}
