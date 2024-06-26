package ru.Onshin.cats;

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
    private String ownerName;
    private List<Integer> catFriendsId = new ArrayList<>();
}
