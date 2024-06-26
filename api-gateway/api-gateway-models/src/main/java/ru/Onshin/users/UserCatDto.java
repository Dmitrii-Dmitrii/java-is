package ru.Onshin.users;

import lombok.Data;
import ru.Onshin.cats.CatDto;

@Data
public class UserCatDto {
    private CatDto catDto;
    private String currentOwnerName;
}
