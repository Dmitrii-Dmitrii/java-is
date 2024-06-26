package ru.Onshin.UserRepositoryGateway;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataAccessible extends JpaRepository<User, Integer> {
    User findUserByName(String name);
}
