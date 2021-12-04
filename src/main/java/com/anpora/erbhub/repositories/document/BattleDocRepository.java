package com.anpora.erbhub.repositories.document;

import com.anpora.erbhub.dao.document.BattleDocDAO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 */
public interface BattleDocRepository extends MongoRepository<BattleDocDAO, String> {

    List<BattleDocDAO> findAll();

}
