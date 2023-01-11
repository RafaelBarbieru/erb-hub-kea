package com.anpora.erbhub.dao;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Entity class for a social medium
 */
@Entity
@Data
@Table(name = "social_media")
public class SocialMediaRelDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}
