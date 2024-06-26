package ru.Onshin.ControllersGateway.Cats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.Onshin.cats.CatDto;
import ru.Onshin.ClientsCats.CatsClient;
import ru.Onshin.cats.Colors;

import java.util.List;

@RestController
@RequestMapping("/admin/cats")
public class AdminCatController {
    private final CatsClient catsClient;

    @Autowired
    public AdminCatController(CatsClient catsClient) {
        this.catsClient = catsClient;
    }

    @GetMapping("/{id}")
    public CatDto getCatById(@PathVariable int id) {
        return catsClient.adminGetCatById(id);
    }

    @GetMapping("/")
    public List<CatDto> getCatsByFilter(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "breed", required = false) String breed,
                                        @RequestParam(value = "color", required = false) Colors color) {
        return catsClient.adminGetCats(name, breed, color);
    }
    
    @PostMapping("/create")
    public void createCat(@RequestBody CatDto catDto) {
        catsClient.createCat(catDto);
    }

    @PutMapping("/update")
    public void updateCat(@RequestBody CatDto catDto) {
        catsClient.updateCat(catDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCat(@PathVariable int id) {
        catsClient.adminDeleteCat(id);
    }
}
