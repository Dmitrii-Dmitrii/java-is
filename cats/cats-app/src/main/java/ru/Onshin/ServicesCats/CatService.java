package ru.Onshin.ServicesCats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.Onshin.RepositoriesCats.Cat;
import ru.Onshin.cats.CatDto;
import ru.Onshin.ModelDtoConverterCats.Converter;
import ru.Onshin.RepositoriesCats.CatDataAccessible;
import ru.Onshin.cats.Colors;
import ru.Onshin.cats.UserCatDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CatService implements CatServiceInterface {
    private final CatDataAccessible catDao;
    private final Converter converter = new Converter();

    @Autowired
    public CatService(CatDataAccessible catDao) {
        this.catDao = catDao;
    }

    public CatDto findCatById(int id) {
        var cat = catDao.findById(id).orElse(null);
        return converter.convertCatModelToDto(cat);
    }

    public CatDto findCatByIdAndOwnerName(int id, String currentOwnerName) {
        var cat = catDao.findCatByIdAndOwnerName(id, currentOwnerName);
        return converter.convertCatModelToDto(cat);
    }

    public List<CatDto> findCatsByOwnerName(String currentOwnerName, String name, String breed, Colors color) {
        List<Cat> cats;
        if (name != null && breed != null && color != null) {
            cats = catDao.findCatsByNameAndBreedAndColorAndOwnerName(name, breed, color, currentOwnerName);
        } else if (name != null && breed != null) {
            cats = catDao.findCatsByNameAndBreedAndOwnerName(name, breed, currentOwnerName);
        } else if (breed != null && color != null) {
            cats = catDao.findCatsByBreedAndColorAndOwnerName(breed, color, currentOwnerName);
        } else if (name != null && color != null) {
            cats = catDao.findCatsByNameAndColorAndOwnerName(name, color, currentOwnerName);
        } else if (name != null) {
            cats = catDao.findCatsByNameAndOwnerName(name, currentOwnerName);
        } else if (breed != null) {
            cats = catDao.findCatsByBreedAndOwnerName(breed, currentOwnerName);
        } else if (color != null) {
            cats = catDao.findCatsByColorAndOwnerName(color, currentOwnerName);
        } else {
            cats = catDao.findCatsByOwnerName(currentOwnerName);
        }

        List<CatDto> result = new ArrayList<>();
        for (Cat catInList : cats) {
            if (currentOwnerName == null || Objects.equals(catInList.getOwnerName(), currentOwnerName)) {
                result.add(converter.convertCatModelToDto(catInList));
            }
        }

        return result;
    }

    public List<CatDto> findCats(String name, String breed, Colors color) {
        List<Cat> cats;
        if (name != null && breed != null && color != null) {
            cats = catDao.findCatsByNameAndBreedAndColor(name, breed, color);
        } else if (name != null && breed != null) {
            cats = catDao.findCatsByNameAndBreed(name, breed);
        } else if (breed != null && color != null) {
            cats = catDao.findCatsByBreedAndColor(breed, color);
        } else if (name != null && color != null) {
            cats = catDao.findCatsByNameAndColor(name, color);
        } else if (name != null) {
            cats = catDao.findCatsByName(name);
        } else if (breed != null) {
            cats = catDao.findCatsByBreed(breed);
        } else if (color != null) {
            cats = catDao.findCatsByColor(color);
        } else {
            cats = catDao.findAll();
        }

        List<CatDto> result = new ArrayList<>();
        for (Cat catInList : cats) {
            result.add(converter.convertCatModelToDto(catInList));
        }

        return result;
    }

    @KafkaListener(topics = "topic-create-cat", groupId = "group-create-cat", containerFactory = "catKafkaListenerContainerFactory")
    public CatDto createCat(UserCatDto userCatDto) {
        var catDto = userCatDto.getCatDto();

        var catsFriends = catDto.getCatFriendsId().stream()
                .map(friendId -> catDao.findById(friendId).orElse(null))
                .toList();
        var cat = converter.convertCatDtoToModel(catDto, catsFriends);
        var savedCat = catDao.save(cat);
        return converter.convertCatModelToDto(savedCat);
    }

    @KafkaListener(topics = "topic-update-cat", groupId = "group-update-cat", containerFactory = "catKafkaListenerContainerFactory")
    public CatDto updateCat(UserCatDto userCatDto) {
        var catDto = userCatDto.getCatDto();

        if (findCatById(catDto.getId()) == null) {
            return null;
        }

        var catsFriends = catDto.getCatFriendsId().stream()
                .map(friendId -> catDao.findById(friendId).orElse(null))
                .toList();
        var cat = converter.convertCatDtoToModel(catDto, catsFriends);
        var savedCat = catDao.save(cat);
        return converter.convertCatModelToDto(savedCat);
    }


    @KafkaListener(topics = "topic-admin-delete-cat", groupId = "group-admin-delete-cat", containerFactory = "catKafkaListenerContainerFactory")
    public boolean deleteCatById(UserCatDto userCatDto) {
        var id = userCatDto.getCatDto().getId();
        var cat = catDao.findById(id).orElse(null);

        if (cat == null) {
            return false;
        } else {
            catDao.delete(cat);
            return true;
        }
    }

    @KafkaListener(topics = "topic-delete-cat", groupId = "group-delete-cat", containerFactory = "catKafkaListenerContainerFactory")
    public boolean deleteCatByIdAndOwnerName(UserCatDto userCatDto) {
        var id = userCatDto.getCatDto().getId();
        var currentOwnerName = userCatDto.getCurrentOwnerName();
        var cat = catDao.findCatByIdAndOwnerName(id, currentOwnerName);

        if (cat == null) {
            return false;
        } else {
            catDao.delete(cat);
            return true;
        }
    }
}
