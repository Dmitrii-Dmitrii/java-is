package ru.Onshin.RepositoriesOwners;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "owners", schema = "public")
public class Owner {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "birth_date")
    private Date birthday;

    @Column(name = "user_id")
    private int userId;
}
