package ru.Onshin.Owners;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDataAccessible extends JpaRepository<Owner, Integer> {
    Owner findByName(String name);
}
