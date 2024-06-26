package ru.Onshin.RepositoriesCats;

import jakarta.persistence.*;
import lombok.Data;
import ru.Onshin.cats.Colors;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "cats", schema = "public")
public class Cat {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthday;

    @Column(name = "breed")
    private String breed;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Colors color;

    @Column(name = "owner_name")
    private String ownerName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cat_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<Cat> catFriends;
}
