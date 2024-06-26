package ru.Onshin.ModelDtoConverterOwners;

import ru.Onshin.RepositoriesOwners.Owner;
import ru.Onshin.owners.OwnerDto;

public class Converter {
    public Owner convertOwnerDtoToModel(OwnerDto ownerDto) {
        var owner = new Owner();
        owner.setId(ownerDto.getId());
        owner.setName(ownerDto.getName());
        owner.setBirthday(ownerDto.getBirthday());
        owner.setUserId(ownerDto.getUserId());

        return owner;
    }

    public OwnerDto convertOwnerModelToDto(Owner owner) {
        var ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        ownerDto.setBirthday(owner.getBirthday());
        ownerDto.setUserId(owner.getUserId());

        return ownerDto;
    }
}
