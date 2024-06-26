package ru.Onshin.Сats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.Onshin.Cats.CatDto;
import ru.Onshin.Cats.CatService;
import ru.Onshin.Cats.Colors;

import java.util.List;

@RestController
@RequestMapping("/admin/cats")
public class AdminCatController {
    private final CatService catService;

    @Autowired
    public AdminCatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/{id}")
    public CatDto getCatById(@PathVariable int id) {
        return catService.findCatById(id);
    }

    @GetMapping("/")
    public List<CatDto> getAllCats() {
        return catService.findAllCats();
    }

    @GetMapping("/name/{name}")
    public CatDto getCatByName(@PathVariable String name) {
        return catService.findCatByName(name);
    }

    @GetMapping("/breed/{breed}")
    public List<CatDto> getCatsByBreed(@PathVariable String breed) {
        return catService.findCatByBreed(breed);
    }

    @GetMapping("/color/{color}")
    public List<CatDto> getCatsByColor(@PathVariable Colors color) {
        return catService.findCatByColor(color);
    }

    @PostMapping("/create")
    public CatDto createCat(@RequestBody CatDto catDto) {
        return catService.createCat(catDto);
    }

    @PutMapping("/update")
    public CatDto updateCat(@RequestBody CatDto catDto) {
        return catService.updateCat(catDto);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCat(@PathVariable int id) {
        return catService.deleteCatById(id);
    }
}
