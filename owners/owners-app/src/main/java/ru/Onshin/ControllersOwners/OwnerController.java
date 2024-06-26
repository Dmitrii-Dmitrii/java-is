package ru.Onshin.ControllersOwners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.Onshin.owners.OwnerDto;
import ru.Onshin.ServicesOwners.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{id}")
    public OwnerDto getOwnerById(@PathVariable int id) {
        return ownerService.findOwnerById(id);
    }

    @GetMapping("/user/{userId}")
    public OwnerDto getOwnerByUserId(@PathVariable int userId) {
        return ownerService.findOwnerByUserId(userId);
    }

    @GetMapping("/")
    public List<OwnerDto> getAllOwners() {
        return ownerService.findAllOwners();
    }

    @PostMapping("/create")
    public void createOwner(@RequestBody OwnerDto userDto) {
        var ownerDto = new OwnerDto();
        ownerDto.setName(userDto.getName());
        ownerDto.setBirthday(userDto.getBirthday());
        ownerDto.setCatsId(userDto.getCatsId());

        ownerService.createOwner(ownerDto);
    }

    @PutMapping("/")
    public void updateOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.updateOwner(ownerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable int id) {
        var ownerDto = new OwnerDto();
        ownerDto.setId(id);
        ownerService.deleteOwner(ownerDto);
    }
}
