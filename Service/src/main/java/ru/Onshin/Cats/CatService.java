package ru.Onshin.Cats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.Onshin.ModelDtoConverter.Converter;
import ru.Onshin.Owners.OwnerDataAccessible;

import java.util.List;

@Service
public class CatService implements CatServiceInterface {
    private final CatDataAccessible catDao;
    private final OwnerDataAccessible ownerDao;
    private final Converter converter = new Converter();

    @Autowired
    public CatService(CatDataAccessible catDao, OwnerDataAccessible ownerDao) {
        this.catDao = catDao;
        this.ownerDao = ownerDao;
    }

    public CatDto findCatById(int id) {
        var cat = catDao.findById(id).orElse(null);
        return converter.convertCatModelToDto(cat);
    }

    public CatDto findCatByIdAndOwnerName(int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        var cat = catDao.findCatByIdAndOwnerName(id, auth.getName());
        return converter.convertCatModelToDto(cat);
    }

    public List<CatDto> findAllCats() {
        return catDao.findAll().stream()
                .map(converter::convertCatModelToDto)
                .toList();
    }


    public List<CatDto> findAllCatsByOwnerName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return catDao.findAllCatsByOwnerName(auth.getName()).stream()
                .map(converter::convertCatModelToDto)
                .toList();
    }


    public CatDto findCatByName(String name) {
        var cat = catDao.findCatByName(name);
        return converter.convertCatModelToDto(cat);
    }

    public CatDto findCatByNameAndOwnerName(String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        var cat = catDao.findCatByNameAndOwnerName(name, auth.getName());
        return converter.convertCatModelToDto(cat);
    }

    public List<CatDto> findCatByBreed(String breed) {
        return catDao.findCatByBreed(breed).stream()
                .map(converter::convertCatModelToDto)
                .toList();
    }


    public List<CatDto> findCatByBreedAndOwnerName(String breed) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return catDao.findCatByBreedAndOwnerName(breed, auth.getName()).stream()
                .map(converter::convertCatModelToDto)
                .toList();
    }


    public List<CatDto> findCatByColor(Colors color) {
        return catDao.findCatByColor(color).stream()
                .map(converter::convertCatModelToDto)
                .toList();
    }


    public List<CatDto> findCatByColorAndOwnerName(Colors color) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return catDao.findCatByColorAndOwnerName(color, auth.getName()).stream()
                .map(converter::convertCatModelToDto)
                .toList();
    }


    public CatDto createCat(CatDto catDto) {
        var owner = ownerDao.findById(catDto.getOwnerId()).orElse(null);
        var catsFriends = catDto.getCatFriendsId().stream()
                .map(friendId -> catDao.findById(friendId).orElse(null))
                .toList();
        var cat = converter.convertCatDtoToModel(catDto, owner, catsFriends);
        var savedCat = catDao.save(cat);
        return converter.convertCatModelToDto(savedCat);
    }

    public CatDto updateCat(CatDto catDto) {
        if (findCatById(catDto.getId()) == null) {
            return null;
        }

        var owner = ownerDao.findById(catDto.getOwnerId()).orElse(null);
        var catsFriends = catDto.getCatFriendsId().stream()
                .map(friendId -> catDao.findById(friendId).orElse(null))
                .toList();
        var cat = converter.convertCatDtoToModel(catDto, owner, catsFriends);
        var savedCat = catDao.save(cat);
        System.out.println(converter.convertCatModelToDto(savedCat).getOwnerId());
        return converter.convertCatModelToDto(savedCat);
    }


    public boolean deleteCatById(int id) {
        var cat = catDao.findById(id).orElse(null);

        if (cat == null) {
            return false;
        } else {
            catDao.delete(cat);
            return true;
        }
    }

    public boolean deleteCatByIdAndOwnerName(int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var cat = catDao.findCatByIdAndOwnerName(id, auth.getName());

        if (cat == null) {
            return false;
        } else {
            catDao.delete(cat);
            return true;
        }
    }
}