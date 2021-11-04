package com.anpora.erbhub.dao.relational;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Entity class for an ERB battle/video/song
 */
@Entity
@Data
@Table(name = "battles")
public class BattleRelDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "publication_date", nullable = false)
    private Date publicationDate;

    @JoinTable(
            name = "battles_characters",
            joinColumns = @JoinColumn(name = "battle_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )

    @ManyToMany(cascade = CascadeType.ALL)
    private List<CharacterRelDAO> characters;


    @Column(name = "lyrics", nullable = false)
    private String lyrics;

    @Column(name = "youtube_link", nullable = false)
    private String youtubeLink;

    @Column(name = "spotify_link")
    private String spotifyLink;

    @Column(name = "cover_image")
    private String coverImageURL;

}
