package ru.Onshin.RepositoriesCats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Onshin.cats.Colors;

import java.util.List;

@Repository
public interface CatDataAccessible extends JpaRepository<Cat, Integer> {
    Cat findCatByIdAndOwnerName(int catId, String ownerName);

    List<Cat> findCatsByNameAndBreedAndColorAndOwnerName(String name, String breed, Colors color, String ownerName);

    List<Cat> findCatsByNameAndBreedAndOwnerName(String name, String breed, String currentOwnerName);

    List<Cat> findCatsByBreedAndColorAndOwnerName(String breed, Colors color, String currentOwnerName);

    List<Cat> findCatsByNameAndColorAndOwnerName(String name, Colors color, String currentOwnerName);

    List<Cat> findCatsByNameAndOwnerName(String name, String currentOwnerName);

    List<Cat> findCatsByBreedAndOwnerName(String breed, String currentOwnerName);

    List<Cat> findCatsByColorAndOwnerName(Colors color, String currentOwnerName);

    List<Cat> findCatsByOwnerName(String currentOwnerName);

    List<Cat> findCatsByNameAndBreedAndColor(String name, String breed, Colors color);

    List<Cat> findCatsByNameAndBreed(String name, String breed);

    List<Cat> findCatsByBreedAndColor(String breed, Colors color);

    List<Cat> findCatsByNameAndColor(String name, Colors color);

    List<Cat> findCatsByName(String name);

    List<Cat> findCatsByBreed(String breed);

    List<Cat> findCatsByColor(Colors color);
}
