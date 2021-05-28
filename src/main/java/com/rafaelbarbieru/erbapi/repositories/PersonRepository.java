package com.rafaelbarbieru.erbapi.repositories;

import com.rafaelbarbieru.erbapi.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM persons P " +
                    "JOIN characters_persons CP ON P.id = CP.person_id " +
                    "WHERE CP.character_id = ?1 AND CP.battle_id = ?2")
    PersonEntity getPersonByCharacterAndBattle(Long characterId, Long battleId);

}
