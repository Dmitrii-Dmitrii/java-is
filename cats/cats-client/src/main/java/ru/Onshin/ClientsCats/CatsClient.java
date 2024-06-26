package ru.Onshin.ClientsCats;

import ru.Onshin.cats.CatDto;
import ru.Onshin.cats.Colors;

import java.util.List;

public interface CatsClient {
    CatDto getCatById(int id, String currentOwnerName);
    List<CatDto> getCats(String currentOwnerName, String name, String breed, Colors color);
    void createCat(CatDto catDto);
    void updateCat(CatDto catDto);
    void deleteCat(int id, String currentOwnerName);
    CatDto adminGetCatById(int id);
    List<CatDto> adminGetCats(String name, String breed, Colors color);
    void adminDeleteCat(int id);
}
