package com.rafaelbarbieru.erbapi.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Rafael Barbieru
 * Entity class for an ERB character
 */
@Entity
@Data
@Table(name = "characters")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @JoinTable(
            name = "characters_persons",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<PersonEntity> persons;


}
