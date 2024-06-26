package ru.Onshin.Owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Onshin.Cats.Cat;
import ru.Onshin.Cats.CatDataAccessible;
import ru.Onshin.ModelDtoConverter.Converter;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService implements OwnerServiceInterface {
    private final OwnerDataAccessible ownerDao;
    private final CatDataAccessible catDao;
    private final Converter converter = new Converter();

    @Autowired
    public OwnerService(OwnerDataAccessible ownerDao, CatDataAccessible catDao) {
        this.ownerDao = ownerDao;
        this.catDao = catDao;
    }

    public OwnerDto findOwnerById(int id) {
        var owner = ownerDao.findById(id).orElse(null);
        return converter.convertOwnerModelToDto(owner);
    }

    public List<OwnerDto> findAllOwners() {
        return ownerDao.findAll().stream()
                .map(converter::convertOwnerModelToDto)
                .toList();
    }

    public OwnerDto createOwner(OwnerDto ownerDto) {
        var cats = ownerDto.getCatsId().stream()
                .map(catId -> catDao.findById(catId).orElse(null))
                .toList();
        var owner = converter.convertOwnerDtoToModel(ownerDto, cats);
        Owner createdOwner = ownerDao.save(owner);
        return converter.convertOwnerModelToDto(createdOwner);
    }

    public OwnerDto updateOwner(OwnerDto ownerDto) {
        if (findOwnerById(ownerDto.getId()) == null) {
            return null;
        }

        var cats = ownerDto.getCatsId().stream()
                .map(catId -> catDao.findById(catId).orElse(null))
                .toList();
        var owner = converter.convertOwnerDtoToModel(ownerDto, cats);
        Owner updatedOwner = ownerDao.save(owner);
        return converter.convertOwnerModelToDto(updatedOwner);
    }


    public boolean deleteOwner(int id) {
        var owner = ownerDao.findById(id).orElse(null);

        if (owner == null) {
            return false;
        } else {
            ownerDao.delete(owner);
            return true;
        }
    }
}
