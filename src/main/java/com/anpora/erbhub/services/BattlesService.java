package com.anpora.erbhub.services;

import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.dto.CharacterDTO;
import com.anpora.erbhub.repositories.BattleRepository;
import com.anpora.erbhub.repositories.PersonRepository;
import com.anpora.erbhub.entities.BattleEntity;
import com.anpora.erbhub.entities.PersonEntity;
import com.anpora.erbhub.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Service class for Battles
 */
@Service
public class BattlesService {

    private static final Logger LOG = LoggerFactory.getLogger(BattlesService.class);
    @Autowired
    private Environment env;

    @Autowired
    private BattleRepository battleRepository;
    @Autowired
    private PersonRepository personRepository;

    /**
     * Returns a list of BattleBean's of all the battles in the database
     *
     * @return
     */
    public List<BattleDTO> getAllBattles() {
        List<BattleDTO> battles = new ArrayList<>();

        // Retrieving all the battles from the database
        battleRepository.findAll().forEach(battleEntity -> battles.add(buildBattleDTO(battleEntity)));

        // Returning the battles
        return battles;
    }

    /**
     * Returns a battle by its ID
     *
     * @param id
     * @return
     */
    public BattleDTO getBattleById(Long id) throws Exception {
        Optional<BattleEntity> battleEntity = battleRepository.findById(id);
        return buildBattleDTO(
                battleEntity.orElseThrow(
                        () -> new ResourceNotFoundException(env.getProperty("error.message.notfound"))
                )
        );
    }

    /**
     * Builds the JSON response
     *
     * @param battleEntity
     * @return
     */
    private BattleDTO buildBattleDTO(BattleEntity battleEntity) {
        List<CharacterDTO> characters = new ArrayList<>();

        // Getting the characters involved in the current battle
        battleEntity.getCharacters().forEach(characterEntity -> {
            PersonEntity personEntity;

            // Filtering out the actors that didn't play the character, in case there are more than one
            if (characterEntity.getPersons().size() > 1) {
                personEntity = personRepository.getPersonByCharacterAndBattle(characterEntity.getId(), battleEntity.getId());
            } else {
                personEntity = characterEntity.getPersons().get(0);
            }

            // Building the character bean
            CharacterDTO characterDTO = CharacterDTO.builder()
                    .id(characterEntity.getId())
                    .character(characterEntity.getName())
                    .name(personEntity.getName())
                    .alias(personEntity.getAlias())
                    .contact(personEntity.getContact())
                    .build();

            // Adding the character to the list of characters in the battle
            characters.add(characterDTO);

        });


        // Building the battle bean
        BattleDTO battle = BattleDTO.builder()
                .id(battleEntity.getId())
                .name(battleEntity.getName())
                .durationInSeconds(battleEntity.getDuration())
                .publicationDate(battleEntity.getPublicationDate())
                .cast(characters)
                .lyrics(battleEntity.getLyrics())
                .spotifyLink(battleEntity.getSpotifyLink())
                .coverPictureURL(battleEntity.getCoverPictureURL())
                .build();

        // Returning the battle
        return battle;
    }

}
