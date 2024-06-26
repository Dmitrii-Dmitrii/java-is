package ru.Onshin.UserServiceGateway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.Onshin.UserRepositoryGateway.Roles;

import java.util.Collection;
import java.util.List;

@Setter
public class UserDetailsImpl implements UserDetails {
    @Getter
    private int id;
    private String name;
    private String password;
    @Getter
    private List<Roles> roles;

    public UserDetailsImpl(int id, String name, String password, List<Roles> roles){
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(Roles::toString).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}