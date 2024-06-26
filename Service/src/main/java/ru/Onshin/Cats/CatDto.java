package ru.Onshin.Cats;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CatDto {
    private int id;
    private String name;
    private Date birthday;
    private String breed;
    private Colors color;
    private int ownerId;
    private List<Integer> catFriendsId = new ArrayList<>();
}
