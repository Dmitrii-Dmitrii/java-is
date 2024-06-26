package ru.Onshin.ControllersGateway.Cats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.Onshin.SecutiryGateway.SecurityService;
import ru.Onshin.cats.CatDto;
import ru.Onshin.cats.Colors;
import ru.Onshin.ClientsCats.CatsClient;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatsClient catsClient;
    private final SecurityService securityService;

    @Autowired
    public CatController(CatsClient catsClient, SecurityService securityService) {
        this.catsClient = catsClient;
        this.securityService = securityService;
    }

    @GetMapping("/{id}")
    public CatDto getCatById(@PathVariable int id) {
        String currentOwnerName = securityService.getCurrentOwnerName();

        return catsClient.getCatById(id, currentOwnerName);
    }

    @GetMapping("/")
    public List<CatDto> getCatsByFilter(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "breed", required = false) String breed,
                                        @RequestParam(value = "color", required = false) Colors color) {
        String currentOwnerName = securityService.getCurrentOwnerName();

        return catsClient.getCats(currentOwnerName, name, breed, color);
    }


    @PostMapping("/")
    public void createCat(@RequestBody CatDto catDto) {
        catsClient.createCat(catDto);
    }

    @PutMapping("/")
    public void updateCat(@RequestBody CatDto catDto) {
        catsClient.updateCat(catDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable int id) {
        String currentOwnerName = securityService.getCurrentOwnerName();

        catsClient.deleteCat(id, currentOwnerName);
    }
}
