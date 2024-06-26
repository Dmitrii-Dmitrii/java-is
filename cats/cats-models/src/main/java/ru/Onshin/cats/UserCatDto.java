package ru.Onshin.cats;

import lombok.Data;

@Data
public class UserCatDto {
    private CatDto catDto;
    private String currentOwnerName;
}
