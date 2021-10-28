package com.anpora.erbhub.repositories;

import com.anpora.erbhub.entities.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 */
public interface ActorRepository extends JpaRepository<ActorEntity, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM actors A " +
                    "JOIN actors_characters AC ON A.id = AC.actor_id " +
                    "WHERE AC.character_id = ?1 AND AC.battle_id = ?2")
    ActorEntity findActorByCharacterAndBattle(Long characterId, Long battleId);

}
