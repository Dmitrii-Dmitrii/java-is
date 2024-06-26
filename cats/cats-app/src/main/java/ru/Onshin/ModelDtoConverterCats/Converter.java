package ru.Onshin.ModelDtoConverterCats;

import ru.Onshin.RepositoriesCats.Cat;
import ru.Onshin.cats.CatDto;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public Cat convertCatDtoToModel(CatDto catDto, List<Cat> catFriends) {
        var cat = new Cat();
        cat.setId(catDto.getId());
        cat.setName(catDto.getName());
        cat.setBirthday(catDto.getBirthday());
        cat.setBreed(catDto.getBreed());
        cat.setColor(catDto.getColor());
        cat.setOwnerName(catDto.getOwnerName());
        cat.setCatFriends(catFriends);

        return cat;
    }

    public CatDto convertCatModelToDto(Cat cat) {
        var catDto = new CatDto();
        catDto.setId(cat.getId());
        catDto.setName(cat.getName());
        catDto.setBirthday(cat.getBirthday());
        catDto.setBreed(cat.getBreed());
        catDto.setColor(cat.getColor());
        catDto.setOwnerName(cat.getOwnerName());

        if (cat.getCatFriends() != null) {
            var catFriendsId = new ArrayList<Integer>();
            for (var catFriend : cat.getCatFriends())
                catFriendsId.add(catFriend.getId());
            catDto.setCatFriendsId(catFriendsId);
        }

        return catDto;
    }
}
