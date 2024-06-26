package ru.Onshin.ControllersGateway.Owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.Onshin.ClientsOwners.OwnersClient;
import ru.Onshin.owners.OwnerDto;
import ru.Onshin.UserServiceGateway.MyUserDetailsService;
import ru.Onshin.UserRepositoryGateway.Roles;
import ru.Onshin.UserServiceGateway.UserDetailsImpl;
import ru.Onshin.users.UserDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnersClient ownersClient;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public OwnerController(OwnersClient ownersClient, MyUserDetailsService userDetailsService) {
        this.ownersClient = ownersClient;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/{id}")
    public OwnerDto getOwnerById(@PathVariable int id) {
        return ownersClient.getOwnerById(id);
    }


    @GetMapping("/")
    public List<OwnerDto> getAllOwners() {
        return ownersClient.getAllOwners();
    }

    @PostMapping("/")
    public void createOwner(@RequestBody UserDto userDto) {
        var roles = new ArrayList<Roles>();
        roles.add(Roles.ROLE_USER);
        var userDetails = new UserDetailsImpl(userDto.getId(), userDto.getName(), userDto.getPassword(), roles);
        var createdUser = userDetailsService.createUser(userDetails);

        var ownerDto = new OwnerDto();
        ownerDto.setName(userDto.getName());
        ownerDto.setBirthday(userDto.getBirthday());
        ownerDto.setCatsId(userDto.getCatsId());
        ownerDto.setUserId(createdUser.getId());

        ownersClient.createOwner(ownerDto);
    }


    @PutMapping("/")
    public void updateOwner(@RequestBody OwnerDto ownerDto) {
        ownersClient.updateOwner(ownerDto);
    }


    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable int id) {
        ownersClient.deleteOwner(id);
    }
}
