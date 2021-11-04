package com.anpora.erbhub.repositories;

import com.anpora.erbhub.dao.relational.BattleRelDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Repository class for the "battles" table
 */
public interface BattleRepository extends JpaRepository<BattleRelDAO, Long> {

    Optional<BattleRelDAO> findById(Long id);
    Optional<BattleRelDAO> findFirstByOrderByPublicationDateDesc();

}
