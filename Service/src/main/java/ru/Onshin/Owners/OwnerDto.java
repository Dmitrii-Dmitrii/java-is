package ru.Onshin.Owners;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OwnerDto {
    private int id;
    private String name;
    private Date birthday;
    private List<Integer> catsId = new ArrayList<>();
}
