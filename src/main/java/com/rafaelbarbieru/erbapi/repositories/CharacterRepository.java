package com.rafaelbarbieru.erbapi.repositories;

import com.rafaelbarbieru.erbapi.entities.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

}
