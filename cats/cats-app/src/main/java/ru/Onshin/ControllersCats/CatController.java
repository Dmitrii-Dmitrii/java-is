package ru.Onshin.ControllersCats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.Onshin.ServicesCats.CatService;
import ru.Onshin.cats.CatDto;
import ru.Onshin.cats.Colors;
import ru.Onshin.ServicesCats.CatServiceInterface;
import ru.Onshin.cats.UserCatDto;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    private final CatServiceInterface catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/{id}")
    public CatDto getCatById(@PathVariable int id, @RequestParam(value = "currentOwnerName") String currentOwnerName) {
        return catService.findCatByIdAndOwnerName(id, currentOwnerName);
    }

    @GetMapping("/")
    public List<CatDto> getCats(@RequestParam(value = "currentOwnerName", required = false) String currentOwnerName,
                                        @RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "breed", required = false) String breed,
                                        @RequestParam(value = "color", required = false) Colors color) {

        return catService.findCatsByOwnerName(currentOwnerName, name, breed, color);
    }

    @PostMapping("/")
    public CatDto createCat(@RequestBody CatDto catDto) {
        var userCatDto = new UserCatDto();
        userCatDto.setCatDto(catDto);
        return catService.createCat(userCatDto);
    }

    @PutMapping("/")
    public CatDto updateCat(@RequestBody CatDto catDto) {
        var userCatDto = new UserCatDto();
        userCatDto.setCatDto(catDto);
        return catService.updateCat(userCatDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCat(@PathVariable int id, @RequestParam(value = "currentOwnerName") String currentOwnerName) {
        var catDto = new CatDto();
        catDto.setId(id);
        var userCatDto = new UserCatDto();
        userCatDto.setCatDto(catDto);
        userCatDto.setCurrentOwnerName(currentOwnerName);

        return catService.deleteCatByIdAndOwnerName(userCatDto);
    }
}
