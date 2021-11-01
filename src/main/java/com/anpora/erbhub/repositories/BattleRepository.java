package com.anpora.erbhub.repositories;

import com.anpora.erbhub.entities.ActorEntity;
import com.anpora.erbhub.entities.BattleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Repository class for the "battles" table
 */
public interface BattleRepository extends JpaRepository<BattleEntity, Long> {

    Optional<BattleEntity> findById(Long id);
    Optional<BattleEntity> findFirstByOrderByPublicationDateDesc();

}
