package com.anpora.erbhub.services;

import com.anpora.erbhub.dto.ActorDTO;
import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.dto.CharacterDTO;
import com.anpora.erbhub.dto.SocialMediaDTO;
import com.anpora.erbhub.dao.relational.SocialMediaRelDAO;
import com.anpora.erbhub.repositories.BattleRepository;
import com.anpora.erbhub.repositories.ActorRepository;
import com.anpora.erbhub.dao.relational.BattleRelDAO;
import com.anpora.erbhub.dao.relational.ActorRelDAO;
import com.anpora.erbhub.exceptions.ResourceNotFoundException;
import com.anpora.erbhub.repositories.SocialMediaRepository;
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
public class BattleService {

    // Dependencies
    private static final Logger LOG = LoggerFactory.getLogger(BattleService.class);
    private Environment env;
    private BattleRepository battleRepository;
    private ActorRepository actorRepository;
    private SocialMediaRepository socialMediaRepository;

    // Constructor injection
    @Autowired
    public BattleService(
            final Environment env,
            BattleRepository battleRepository,
            ActorRepository actorRepository,
            SocialMediaRepository socialMediaRepository) {
        this.env = env;
        this.battleRepository = battleRepository;
        this.actorRepository = actorRepository;
        this.socialMediaRepository = socialMediaRepository;
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
        Optional<BattleRelDAO> battleEntity = battleRepository.findById(id);
        return buildBattleDTO(
                battleEntity.orElseThrow(
                        () -> new ResourceNotFoundException(env.getProperty("error.message.notfound"))
                )
        );
    }

    /**
     * Gets the latest battle according to the publication date
     *
     * @return
     * @throws Exception
     */
    public BattleDTO getLastBattle() throws Exception {
        Optional<BattleRelDAO> battleEntity = battleRepository.findFirstByOrderByPublicationDateDesc();
        return buildBattleDTO(
                battleEntity.orElseThrow(
                        () -> new ResourceNotFoundException(env.getProperty("error.message.notfound"))
                )
        );
    }

    /**
     * Builds the battle DTO
     *
     * @param battleEntity
     * @return
     */
    private BattleDTO buildBattleDTO(BattleRelDAO battleEntity) {
        try {
            List<CharacterDTO> characters = new ArrayList<>();

            // Getting the characters involved in the current battle
            battleEntity.getCharacters().forEach(characterEntity -> {
                List<ActorRelDAO> actorsEntity = new ArrayList<>();

                // Filtering out the actors that didn't play the character, in case there is more than one
                if (characterEntity.getActors().size() > 1) {

                    actorsEntity = actorRepository.findActorsByCharacterAndBattle(
                            characterEntity.getId(),
                            battleEntity.getId()
                    );
                } else {
                    actorsEntity.add(characterEntity.getActors().get(0));
                }

                // Building the actor bean
                ActorRelDAO actorEntity = actorsEntity.get(0);//TODO: Make this work for more than one actor as well
                ActorDTO actorDTO = ActorDTO.builder()
                        .id(actorEntity.getId())
                        .name(actorEntity.getName())
                        .alias(actorEntity.getAlias())
                        .description(actorEntity.getDescription())
                        .imageURL(actorEntity.getImageURL())
                        .socialMedia(null) // We don't need the social media for this
                        .build();

                List<ActorDTO> actors = new ArrayList<>();
                actors.add(actorDTO);

                // Building the character bean
                CharacterDTO characterDTO = CharacterDTO.builder()
                        .id(characterEntity.getId())
                        .name(characterEntity.getName())
                        .description(characterEntity.getDescription())
                        .image(characterEntity.getImageURL())
                        .actors(actors)
                        .build();

                // Adding the character to the list of characters in the battle
                characters.add(characterDTO);

            });


            // Building the battle bean
            BattleDTO battle = BattleDTO.builder()
                    .id(battleEntity.getId())
                    .name(battleEntity.getName())
                    .duration(battleEntity.getDuration())
                    .publicationDate(battleEntity.getPublicationDate())
                    .lyrics(battleEntity.getLyrics())
                    .youtubeLink(battleEntity.getYoutubeLink())
                    .spotifyLink(battleEntity.getSpotifyLink())
                    .image(battleEntity.getCoverImageURL())
                    .characters(characters)
                    .build();

            // Returning the battle
            return battle;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
