package ru.Onshin.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataAccessible extends JpaRepository<User, Integer> {
    User findByOwnerName(String ownerName);
}
