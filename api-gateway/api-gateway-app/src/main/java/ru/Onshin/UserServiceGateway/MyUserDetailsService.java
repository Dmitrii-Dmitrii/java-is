package ru.Onshin.UserServiceGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.Onshin.UserRepositoryGateway.User;
import ru.Onshin.UserRepositoryGateway.UserDataAccessible;
import ru.Onshin.users.UserDto;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDataAccessible userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String ownerName) throws UsernameNotFoundException {
        User user = userDao.findUserByName(ownerName);

        if (user == null)
            throw new UsernameNotFoundException(ownerName + " not found");

        return new UserDetailsImpl(user.getId(), ownerName, user.getPassword(), user.getRoles());
    }

    public UserDetailsImpl createUser(UserDetailsImpl userDetails) {
        var user = UserDetailsToUser(userDetails);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var createdUser = userDao.save(user);
        return new UserDetailsImpl(createdUser.getId(), createdUser.getName(), createdUser.getPassword(), createdUser.getRoles());
    }

    private User UserDetailsToUser(UserDetailsImpl userDetails) {
        var user = new User();
        user.setName(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setRoles(userDetails.getRoles());

        return user;
    }

    public UserDto findUserByName(String ownerName) {
        User user = userDao.findUserByName(ownerName);

        if (user == null)
            return null;

        var userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(ownerName);
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
