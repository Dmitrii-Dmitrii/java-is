package ru.Onshin.Owners;

import java.util.List;

public interface OwnerServiceInterface {
    OwnerDto findOwnerById(int id);
    List<OwnerDto> findAllOwners();
    OwnerDto createOwner(OwnerDto ownerDto);
    OwnerDto updateOwner(OwnerDto ownerDto);
    boolean deleteOwner(int id);
}
