package com.anpora.erbhub.repositories;

import com.anpora.erbhub.dao.relational.ActorRelDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 */
public interface ActorRepository extends JpaRepository<ActorRelDAO, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM actors A " +
            "JOIN actors_characters AC ON A.id = AC.actor_id " +
             "WHERE AC.character_id = ?1 AND AC.battle_id = ?2")
    ActorRelDAO findActorByCharacterAndBattle(Long characterId, Long battleId);

    @Query(nativeQuery = true, value =
            "SELECT * FROM actors A " +
            "JOIN actors_characters AC on A.id = AC.actor_id " +
            "WHERE AC.character_id = ?1")
    List<ActorRelDAO> findActorByCharacter(Long characterId);

}
