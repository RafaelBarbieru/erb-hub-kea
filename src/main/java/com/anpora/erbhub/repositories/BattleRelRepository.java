package com.anpora.erbhub.repositories;

import com.anpora.erbhub.dao.BattleRelDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Repository class for the "battles" table
 */
public interface BattleRelRepository extends JpaRepository<BattleRelDAO, Long> {

    Optional<BattleRelDAO> findById(Long id);

    @Query(nativeQuery = true, value =
            "SELECT B.* FROM battles B " +
            "JOIN battles_characters BC ON B.id = BC.battle_id " +
            "JOIN characters C ON C.id = BC.character_id " +
            "WHERE C.id = ?1"
    )
    List<BattleRelDAO> getBattleByCharacterId(Long id);

    @Query(nativeQuery = true, value =
            "SELECT B.* FROM battles B " +
                    "JOIN battles_characters BC ON B.id = BC.battle_id " +
                    "JOIN characters C ON C.id = BC.character_id " +
                    "JOIN actors_characters AC ON C.id = AC.character_id " +
                    "JOIN actors A on A.id = AC.actor_id " +
                    "WHERE A.id = ?1"
    )
    List<BattleRelDAO> getBattlesFromActor(Long actorId);

}
