package ru.Onshin.ControllersGateway.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Onshin.ClientsOwners.OwnersClient;
import ru.Onshin.UserServiceGateway.UserDetailsImpl;
import ru.Onshin.owners.OwnerDto;
import ru.Onshin.users.UserDto;
import ru.Onshin.UserRepositoryGateway.Roles;
import ru.Onshin.UserServiceGateway.MyUserDetailsService;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class UserController {
    private final OwnersClient ownersClient;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public UserController(OwnersClient ownersClient, MyUserDetailsService userDetailsService) {
        this.ownersClient = ownersClient;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("users/")
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
}
