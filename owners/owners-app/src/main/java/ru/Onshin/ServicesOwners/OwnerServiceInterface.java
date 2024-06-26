package ru.Onshin.ServicesOwners;

import ru.Onshin.owners.OwnerDto;

import java.util.List;

public interface OwnerServiceInterface {
    OwnerDto findOwnerById(int id);
    List<OwnerDto> findAllOwners();
    void createOwner(OwnerDto ownerDto);
    void updateOwner(OwnerDto ownerDto);
    void deleteOwner(OwnerDto ownerDto);
}
