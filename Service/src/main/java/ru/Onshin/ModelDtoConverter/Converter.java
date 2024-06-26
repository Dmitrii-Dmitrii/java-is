package ru.Onshin.ModelDtoConverter;

import ru.Onshin.Cats.Cat;
import ru.Onshin.Cats.CatDto;
import ru.Onshin.Owners.Owner;
import ru.Onshin.Owners.OwnerDto;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public Cat convertCatDtoToModel(CatDto catDto, Owner owner, List<Cat> catFriends) {
        var cat = new Cat();
        cat.setId(catDto.getId());
        cat.setName(catDto.getName());
        cat.setBirthday(catDto.getBirthday());
        cat.setBreed(catDto.getBreed());
        cat.setColor(catDto.getColor());
        cat.setOwner(owner);
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

        if (cat.getOwner() != null)
            catDto.setOwnerId(cat.getOwner().getId());

        if (cat.getCatFriends() != null) {
            var catFriendsId = new ArrayList<Integer>();
            for (var catFriend : cat.getCatFriends())
                catFriendsId.add(catFriend.getId());
            catDto.setCatFriendsId(catFriendsId);
        }

        return catDto;
    }

    public Owner convertOwnerDtoToModel(OwnerDto ownerDto, List<Cat> cats) {
        var owner = new Owner();
        owner.setId(ownerDto.getId());
        owner.setName(ownerDto.getName());
        owner.setBirthday(ownerDto.getBirthday());
        owner.setCats(cats);

        return owner;
    }

    public OwnerDto convertOwnerModelToDto(Owner owner) {
        var ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        ownerDto.setBirthday(owner.getBirthday());

        if (owner.getCats() != null) {
            var catsId = new ArrayList<Integer>();
            for (var cat : owner.getCats())
                catsId.add(cat.getId());
            ownerDto.setCatsId(catsId);
        }

        return ownerDto;
    }
}
