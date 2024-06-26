package ru.Onshin.Owners;

import lombok.Data;
import jakarta.persistence.*;
import ru.Onshin.Cats.Cat;

import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "owners", schema = "public")
public class Owner {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthday;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Cat> cats;
}
