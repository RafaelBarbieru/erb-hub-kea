package com.anpora.erbhub.services;

import com.anpora.erbhub.dao.relational.ActorRelDAO;
import com.anpora.erbhub.dao.relational.SocialMediaRelDAO;
import com.anpora.erbhub.dto.ActorDTO;
import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.dto.CharacterDTO;
import com.anpora.erbhub.dto.SocialMediaDTO;
import com.anpora.erbhub.exceptions.ResourceNotFoundException;
import com.anpora.erbhub.repositories.relational.ActorRelRepository;
import com.anpora.erbhub.repositories.relational.BattleRelRepository;
import com.anpora.erbhub.repositories.relational.CharacterRelRepository;
import com.anpora.erbhub.repositories.relational.SocialMediaRelRepository;
import com.anpora.erbhub.utils.DTOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    // Dependencies
    private Environment env;
    private BattleRelRepository battleRelRepository;
    private CharacterRelRepository characterRelRepository;
    private ActorRelRepository actorRelRepository;
    private SocialMediaRelRepository socialMediaRelRepository;

    // Constructor injection
    @Autowired
    public ActorService(
            final Environment env,
            final BattleRelRepository battleRelRepository,
            final CharacterRelRepository characterRelRepository,
            ActorRelRepository actorRelRepository,
            SocialMediaRelRepository socialMediaRelRepository) {
        this.env = env;
        this.battleRelRepository = battleRelRepository;
        this.characterRelRepository = characterRelRepository;
        this.actorRelRepository = actorRelRepository;
        this.socialMediaRelRepository = socialMediaRelRepository;
    }

    // Methods

    /**
     * Returns a list of ActorBean's of all the battles in the database
     *
     * @return
     */
    public List<ActorDTO> getAllActors() {
        List<ActorDTO> actors = new ArrayList<>();

        // Retrieving all the actors from the database
        actorRelRepository.findAll().forEach(actor -> actors.add(buildActorDTO(actor)));

        // Returning the actors
        return actors;
    }

    public List<BattleDTO> getBattlesOfActor(Long actorId) {

        List<BattleDTO> battles = new ArrayList<>();

        // Retrieving all the battles for this actor
        battleRelRepository.getBattlesFromActor(actorId).forEach(
                battle -> battles.add(DTOBuilder.buildBattleSimple(battle))
        );

        return battles;
    }

    public List<CharacterDTO> getCharactersOfActor(Long actorId) {

        List<CharacterDTO> characters = new ArrayList<>();

        // Retrieving all the characters for this actor
        characterRelRepository.getCharactersFromActor(actorId).forEach(
                character -> characters.add(DTOBuilder.buildCharacterSimple(character))
        );

        return characters;
    }

    /**
     * Returns an actor by its ID
     *
     * @param id
     * @return
     */
    public ActorDTO getActorById(Long id) throws Exception {
        Optional<ActorRelDAO> battleEntity = actorRelRepository.findById(id);
        return buildActorDTO(
                battleEntity.orElseThrow(
                        () -> new ResourceNotFoundException(env.getProperty("error.message.notfound"))
                )
        );
    }

    /**
     * Builds the actor DTO
     *
     * @param actorEntity
     * @return
     */
    public ActorDTO buildActorDTO(ActorRelDAO actorEntity) {

        List<SocialMediaRelDAO> socialMediaDAOList = socialMediaRelRepository
                .findSocialMediaByActorID(actorEntity.getId());

        // Building the social media DTO
        List<SocialMediaDTO> socialMedia = new ArrayList<>();
        socialMediaDAOList.forEach(account -> {
                String link = socialMediaRelRepository
                        .getLinkByActorIDAndSocialMediaID(actorEntity.getId(), account.getId());
            socialMedia.add(DTOBuilder.buildSocialMedia(account, link));
        });

        // Building the actor DTO for each actor entity found
        ActorDTO actorDTO = DTOBuilder.buildActorFull(actorEntity, socialMedia);

        return actorDTO;
}

}
