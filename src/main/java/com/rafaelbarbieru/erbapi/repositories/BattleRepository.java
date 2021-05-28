package com.rafaelbarbieru.erbapi.repositories;

import com.rafaelbarbieru.erbapi.entities.BattleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Rafael Barbieru
 * Repository class for the "battles" table
 */
public interface BattleRepository extends JpaRepository<BattleEntity, Long> {

    Optional<BattleEntity> findById(Long id);

}
