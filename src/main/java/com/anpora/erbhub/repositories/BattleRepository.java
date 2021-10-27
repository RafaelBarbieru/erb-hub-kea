package com.anpora.erbhub.repositories;

import com.anpora.erbhub.entities.BattleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Repository class for the "battles" table
 */
public interface BattleRepository extends JpaRepository<BattleEntity, Long> {

    Optional<BattleEntity> findById(Long id);

}
