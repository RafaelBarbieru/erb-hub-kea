package com.anpora.erbhub.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Entity class for an ERB person
 */
@Entity
@Data
@Table(name = "persons")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "alias")
    private String alias;
    @Column(name = "contact")
    private String contact;

}
