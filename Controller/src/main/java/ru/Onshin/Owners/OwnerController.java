package ru.Onshin.Owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.Onshin.Users.MyUserDetailsService;
import ru.Onshin.Users.Roles;
import ru.Onshin.Users.UserDetailsImpl;
import ru.Onshin.Users.UserDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public OwnerController(OwnerService ownerService, MyUserDetailsService userDetailsService) {
        this.ownerService = ownerService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/{id}")
    public OwnerDto getOwnerById(@PathVariable int id) {
        return ownerService.findOwnerById(id);
    }

    @GetMapping("/")
    public List<OwnerDto> getAllOwners() {
        return ownerService.findAllOwners();
    }

    @PostMapping("/create")
    public OwnerDto createOwner(@RequestBody UserDto userDto) {
        var ownerDto = new OwnerDto();
        ownerDto.setName(userDto.getName());
        ownerDto.setBirthday(userDto.getBirthday());
        ownerDto.setCatsId(userDto.getCatsId());

        var createdOwner = ownerService.createOwner(ownerDto);
        var roles = new ArrayList<Roles>();
        roles.add(Roles.ROLE_USER);
        var userDetails = new UserDetailsImpl(userDto.getName(), userDto.getPassword(), roles);
        userDetailsService.createUser(userDetails);

        return createdOwner;
    }

    @PutMapping("/")
    public OwnerDto updateOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.updateOwner(ownerDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteOwner(@PathVariable int id) {
        return ownerService.deleteOwner(id);
    }
}
