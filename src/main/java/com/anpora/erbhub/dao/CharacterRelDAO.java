package com.anpora.erbhub.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Entity class for an ERB character
 */
@Entity
@Data
@Table(name = "characters")

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Entity class for a character
 */
public class CharacterRelDAO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = false)
    private String imageURL;

    @JoinTable(
            name = "actors_characters",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<ActorRelDAO> actors;


}
