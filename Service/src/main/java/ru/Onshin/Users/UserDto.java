package ru.Onshin.Users;

import lombok.Data;
import ru.Onshin.Owners.OwnerDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private int id;
    private String name;
    private Date birthday;
    private List<Integer> catsId = new ArrayList<>();
    private String password;
}
