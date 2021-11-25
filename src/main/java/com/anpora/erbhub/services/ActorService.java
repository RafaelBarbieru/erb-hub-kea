package com.anpora.erbhub.services;

import com.anpora.erbhub.dao.relational.ActorRelDAO;
import com.anpora.erbhub.dao.relational.SocialMediaRelDAO;
import com.anpora.erbhub.dto.ActorDTO;
import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.dto.CharacterDTO;
import com.anpora.erbhub.dto.SocialMediaDTO;
import com.anpora.erbhub.exceptions.ResourceNotFoundException;
import com.anpora.erbhub.repositories.ActorRepository;
import com.anpora.erbhub.repositories.BattleRepository;
import com.anpora.erbhub.repositories.CharacterRepository;
import com.anpora.erbhub.repositories.SocialMediaRepository;
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
    private BattleRepository battleRepository;
    private CharacterRepository characterRepository;
    private ActorRepository actorRepository;
    private SocialMediaRepository socialMediaRepository;

    // Constructor injection
    @Autowired
    public ActorService(
            final Environment env,
            final BattleRepository battleRepository,
            final CharacterRepository characterRepository,
            ActorRepository actorRepository,
            SocialMediaRepository socialMediaRepository) {
        this.env = env;
        this.battleRepository = battleRepository;
        this.characterRepository = characterRepository;
        this.actorRepository = actorRepository;
        this.socialMediaRepository = socialMediaRepository;
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
        actorRepository.findAll().forEach(actor -> actors.add(buildActorDTO(actor)));

        // Returning the actors
        return actors;
    }

    public List<BattleDTO> getBattlesOfActor(Long actorId) {

        List<BattleDTO> battles = new ArrayList<>();

        // Retrieving all the battles for this actor
        battleRepository.getBattlesFromActor(actorId).forEach(
                battle -> battles.add(DTOBuilder.buildBattleSimple(battle))
        );

        return battles;
    }

    public List<CharacterDTO> getCharactersOfActor(Long actorId) {

        List<CharacterDTO> characters = new ArrayList<>();

        // Retrieving all the characters for this actor
        characterRepository.getCharactersFromActor(actorId).forEach(
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
        Optional<ActorRelDAO> battleEntity = actorRepository.findById(id);
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

        List<SocialMediaRelDAO> socialMediaDAOList = socialMediaRepository
                .findSocialMediaByActorID(actorEntity.getId());

        // Building the social media DTO
        List<SocialMediaDTO> socialMedia = new ArrayList<>();
        socialMediaDAOList.forEach(account -> {
                String link = socialMediaRepository
                        .getLinkByActorIDAndSocialMediaID(actorEntity.getId(), account.getId());
            socialMedia.add(DTOBuilder.buildSocialMedia(account, link));
        });

        // Building the actor DTO for each actor entity found
        ActorDTO actorDTO = DTOBuilder.buildActorFull(actorEntity, socialMedia);

        return actorDTO;
}

}
