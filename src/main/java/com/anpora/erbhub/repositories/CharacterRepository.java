package com.anpora.erbhub.repositories;

import com.anpora.erbhub.entities.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 */
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

}
