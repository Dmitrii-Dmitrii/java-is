package ru.Onshin.Cats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatDataAccessible extends JpaRepository<Cat, Integer> {
    Cat findCatByIdAndOwnerName(int catId, String ownerName);
    List<Cat> findAllCatsByOwnerName(String ownerName);
    Cat findCatByName(String catName);
    Cat findCatByNameAndOwnerName(String catName, String ownerName);
    List<Cat> findCatByBreed(String breed);
    List<Cat> findCatByBreedAndOwnerName(String breed, String ownerName);
    List<Cat> findCatByColor(Colors color);
    List<Cat> findCatByColorAndOwnerName(Colors color, String ownerName);
}
