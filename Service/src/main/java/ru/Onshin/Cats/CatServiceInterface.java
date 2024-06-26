package ru.Onshin.Cats;

import java.util.List;

public interface CatServiceInterface {
    CatDto findCatById(int id);
    CatDto findCatByIdAndOwnerName(int id);
    List<CatDto> findAllCats();
    List<CatDto> findAllCatsByOwnerName();
    CatDto findCatByName(String name);
    CatDto findCatByNameAndOwnerName(String name);
    List<CatDto> findCatByBreed(String breed);
    List<CatDto> findCatByBreedAndOwnerName(String breed);
    List<CatDto> findCatByColor(Colors color);
    List<CatDto> findCatByColorAndOwnerName(Colors color);
    CatDto createCat(CatDto catDto);
    CatDto updateCat(CatDto catDto);
    boolean deleteCatById(int id);
    boolean deleteCatByIdAndOwnerName(int id);
}
