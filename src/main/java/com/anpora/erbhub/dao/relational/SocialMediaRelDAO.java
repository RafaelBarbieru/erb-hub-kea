package com.anpora.erbhub.dao.relational;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Entity class for a social medium
 */
@Entity
@Data
@Table(name = "social_media")
@SecondaryTable(
        name = "actors_social_media",
        pkJoinColumns=@PrimaryKeyJoinColumn(name="social_media_id", referencedColumnName="id")
)
public class SocialMediaRelDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(table="actors_social_media", name="link", nullable = false)
    private String link;

}
