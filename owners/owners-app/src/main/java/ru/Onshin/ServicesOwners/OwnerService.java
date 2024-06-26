package ru.Onshin.ServicesOwners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.Onshin.RepositoriesOwners.Owner;
import ru.Onshin.RepositoriesOwners.OwnerDataAccessible;
import ru.Onshin.cats.CatDto;
import ru.Onshin.ClientsCats.CatsClient;
import ru.Onshin.ModelDtoConverterOwners.Converter;
import ru.Onshin.owners.OwnerDto;

import java.util.List;

@Service
public class OwnerService implements OwnerServiceInterface {
    private final OwnerDataAccessible ownerDao;
    private final CatsClient catsClient;
    private final Converter converter = new Converter();

    @Autowired
    public OwnerService(OwnerDataAccessible ownerDao, CatsClient catsClient) {
        this.ownerDao = ownerDao;
        this.catsClient = catsClient;
    }

    public OwnerDto findOwnerById(int id) {
        var owner = ownerDao.findById(id).orElse(null);
        var ownerDto = converter.convertOwnerModelToDto(owner);
        var catDtos = catsClient.getCats(ownerDto.getName(), null, null, null);
        List<Integer> catIds = catDtos.stream()
                .map(CatDto::getId)
                .toList();
        ownerDto.setCatsId(catIds);
        return ownerDto;
    }

    public OwnerDto findOwnerByUserId(int userId) {
        var owner = ownerDao.findByUserId(userId);
        var ownerDto = converter.convertOwnerModelToDto(owner);
        var catDtos = catsClient.getCats(ownerDto.getName(), null, null, null);
        List<Integer> catIds = catDtos.stream()
                .map(CatDto::getId)
                .toList();
        ownerDto.setCatsId(catIds);
        return ownerDto;
    }


    public List<OwnerDto> findAllOwners() {
        var ownerDtos = ownerDao.findAll().stream()
                .map(converter::convertOwnerModelToDto)
                .toList();
        for (var ownerDto : ownerDtos) {
            var catDtos = catsClient.getCats(ownerDto.getName(), null, null, null);
            List<Integer> catIds = catDtos.stream()
                    .map(CatDto::getId)
                    .toList();
            ownerDto.setCatsId(catIds);
        }
        return ownerDtos;
    }

    @KafkaListener(topics = "topic-create-owner", groupId = "group-create-owner", containerFactory = "ownerKafkaListenerContainerFactory")
    public void createOwner(OwnerDto ownerDto) {
        var owner = converter.convertOwnerDtoToModel(ownerDto);
        Owner createdOwner = ownerDao.save(owner);
        converter.convertOwnerModelToDto(createdOwner);
    }

    @KafkaListener(topics = "topic-update-owner", groupId = "group-update-owner", containerFactory = "ownerKafkaListenerContainerFactory")
    public void updateOwner(OwnerDto ownerDto) {
        if (findOwnerById(ownerDto.getId()) == null) {
            return;
        }

        var owner = converter.convertOwnerDtoToModel(ownerDto);
        Owner updatedOwner = ownerDao.save(owner);
        converter.convertOwnerModelToDto(updatedOwner);
    }

    @KafkaListener(topics = "topic-delete-owner", groupId = "group-delete-owner", containerFactory = "ownerKafkaListenerContainerFactory")
    public void deleteOwner(OwnerDto ownerDto) {
        var owner = ownerDao.findById(ownerDto.getId()).orElse(null);

        if (owner != null) {
            ownerDao.delete(owner);
        }
    }
}
