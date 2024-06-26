package ru.Onshin.ServicesCats;

import ru.Onshin.cats.CatDto;
import ru.Onshin.cats.Colors;
import ru.Onshin.cats.UserCatDto;

import java.util.List;

public interface CatServiceInterface {
    CatDto findCatById(int id);
    CatDto findCatByIdAndOwnerName(int id, String currentOwnerName);
    List<CatDto> findCatsByOwnerName(String currentOwnerName, String name, String breed, Colors color);
    List<CatDto> findCats(String name, String breed, Colors color);
    CatDto createCat(UserCatDto userCatDto);
    CatDto updateCat(UserCatDto userCatDto);
    boolean deleteCatById(UserCatDto userCatDto);
    boolean deleteCatByIdAndOwnerName(UserCatDto userCatDto);
}
