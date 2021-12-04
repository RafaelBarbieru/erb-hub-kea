package com.anpora.erbhub.services;

import com.anpora.erbhub.dao.document.BattleDocDAO;
import com.anpora.erbhub.dto.ActorDTO;
import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.dto.CharacterDTO;
import com.anpora.erbhub.repositories.document.BattleDocRepository;
import com.anpora.erbhub.repositories.relational.BattleRelRepository;
import com.anpora.erbhub.repositories.relational.ActorRelRepository;
import com.anpora.erbhub.dao.relational.BattleRelDAO;
import com.anpora.erbhub.dao.relational.ActorRelDAO;
import com.anpora.erbhub.exceptions.ResourceNotFoundException;
import com.anpora.erbhub.repositories.relational.SocialMediaRelRepository;
import com.anpora.erbhub.utils.DTOBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Service class for Battles
 */
@Service
public class BattleService {

    // Dependencies
    private static final Logger LOG = LoggerFactory.getLogger(BattleService.class);
    private Environment env;
    private BattleRelRepository battleRelRepository;
    private BattleDocRepository battleDocRepository;
    private ActorRelRepository actorRelRepository;
    private SocialMediaRelRepository socialMediaRelRepository;



    // Constructor injection
    @Autowired
    public BattleService(
            final Environment env,
            BattleRelRepository battleRelRepository,
            BattleDocRepository battleDocRepository,
            ActorRelRepository actorRelRepository,
            SocialMediaRelRepository socialMediaRelRepository) {
        this.env = env;
        this.battleRelRepository = battleRelRepository;
        this.battleDocRepository = battleDocRepository;
        this.actorRelRepository = actorRelRepository;
        this.socialMediaRelRepository = socialMediaRelRepository;
    }

    // Methods

    /**
     * Returns a list of BattleBean's of all the battles in the database
     *
     * @return
     */
    public List<BattleDTO> getAllBattles() {
        List<BattleDTO> battles = new ArrayList<>();

        // Retrieving all the battles from the database
        battleRelRepository.findAll().forEach(battleEntity -> battles.add(buildBattleDTO(battleEntity)));

        // Returning the battles
        return battles;
    }

    /**
     * Returns all of the battles using the document repository.
     *
     * @return
     */
    public List<BattleDocDAO> getAllBattlesDoc() {
        List<BattleDocDAO> battles = battleDocRepository.findAll();
        return battles;
    }

    /**
     * Returns a battle by its ID
     *
     * @param id
     * @return
     */
    public BattleDTO getBattleById(Long id) throws Exception {
        Optional<BattleRelDAO> battleEntity = battleRelRepository.findById(id);
        return buildBattleDTO(
                battleEntity.orElseThrow(
                        () -> new ResourceNotFoundException(env.getProperty("error.message.notfound"))
                )
        );
    }

    public BattleDocDAO getBattleByIdDoc(String id) throws Exception {
        return battleDocRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(env.getProperty("error.message.notfound"))
        );
    }

    public List<ActorDTO> getActorsOfBattle(Long id) {
        List<ActorDTO> actors = new ArrayList<>();

        // Retrieving all the characters for this actor
        actorRelRepository.getActorsFromBattle(id).forEach(
                actor -> actors.add(DTOBuilder.buildActorSimple(actor))
        );

        return actors;
    }

    /**
     * Gets all the battles in which the specified character has played.
     *
     * @return a list of all the battles in which the character has played.
     * @throws Exception
     */
    public List<BattleDTO> getBattlesByCharacterId(Long id) throws Exception {
        List<BattleRelDAO> battleEntity = battleRelRepository.getBattleByCharacterId(id);
        List<BattleDTO> battles = new ArrayList<>();
        battleEntity.forEach(battle -> {
            BattleDTO battleDTO = BattleDTO.builder()
                    .id(battle.getId())
                    .name(battle.getName())
                    .build();
            battles.add(battleDTO);
        });
        return battles;
    }

    /**
     * Builds the battle DTO
     *
     * @param battleEntity
     * @return the battle DTO
     */
    private BattleDTO buildBattleDTO(BattleRelDAO battleEntity) {
        try {
            List<CharacterDTO> characters = new ArrayList<>();

            // Getting the characters involved in the current battle
            battleEntity.getCharacters().forEach(characterEntity -> {

                // Filtering out the actors that didn't play the character, in case there is more than one
                List<ActorRelDAO> actorsEntity = actorRelRepository.findActorsByCharacterAndBattle(
                        characterEntity.getId(),
                        battleEntity.getId()
                );

                // Building the actor beans
                List<ActorDTO> actors = new ArrayList<>();
                actorsEntity.forEach(DTOBuilder::buildActorSimple);

                // Building the character bean
                CharacterDTO characterDTO = DTOBuilder.buildCharacterFull(characterEntity, actors);

                // Adding the character to the list of characters in the battle
                characters.add(characterDTO);

            });


            // Building the battle bean
            BattleDTO battle = DTOBuilder.buildBattleFull(battleEntity, characters);

            // Returning the battle
            return battle;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
