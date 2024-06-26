package ru.Onshin.ClientsOwners;

import ru.Onshin.owners.OwnerDto;

import java.util.List;

public interface OwnersClient {
    OwnerDto getOwnerById(int id);
    OwnerDto getOwnerByUserId(int id);
    List<OwnerDto> getAllOwners();
    void createOwner(OwnerDto userDto);
    void updateOwner(OwnerDto ownerDto);
    void deleteOwner(int id);
}
