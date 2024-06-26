package ru.Onshin.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.Onshin.Owners.OwnerDataAccessible;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDataAccessible userDao;
    @Autowired
    private OwnerDataAccessible ownerDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String ownerName) throws UsernameNotFoundException {
        User user = userDao.findByOwnerName(ownerName);

        if (user == null)
            throw new UsernameNotFoundException(ownerName + " not found");

        return new UserDetailsImpl(ownerName, user.getPassword(), user.getRoles());
    }

    public UserDetailsImpl createUser(UserDetailsImpl userDetails) {
        var user = UserDetailsToUser(userDetails);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var createdUser = userDao.save(user);
        return new UserDetailsImpl(createdUser.getOwner().getName(), createdUser.getPassword(), createdUser.getRoles());
    }

    private User UserDetailsToUser(UserDetailsImpl userDetails) {
        var user = new User();
        var owner = ownerDao.findByName(userDetails.getUsername());
        user.setOwner(owner);
        user.setPassword(userDetails.getPassword());
        user.setRoles(userDetails.getRoles());

        return user;
    }
}
