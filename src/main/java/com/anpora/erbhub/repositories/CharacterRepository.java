package com.anpora.erbhub.repositories;

import com.anpora.erbhub.dao.relational.CharacterRelDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 */
public interface CharacterRepository extends JpaRepository<CharacterRelDAO, Long> {

    Optional<CharacterRelDAO> findById(Long id);

}
