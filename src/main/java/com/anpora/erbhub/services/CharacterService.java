package com.anpora.erbhub.services;

import com.anpora.erbhub.dao.relational.ActorRelDAO;
import com.anpora.erbhub.dao.relational.CharacterRelDAO;
import com.anpora.erbhub.dao.relational.SocialMediaRelDAO;
import com.anpora.erbhub.dto.ActorDTO;
import com.anpora.erbhub.dto.CharacterDTO;
import com.anpora.erbhub.dto.SocialMediaDTO;
import com.anpora.erbhub.exceptions.ResourceNotFoundException;
import com.anpora.erbhub.repositories.ActorRepository;
import com.anpora.erbhub.repositories.CharacterRepository;
import com.anpora.erbhub.repositories.SocialMediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    // Dependencies
    private static final Logger LOG = LoggerFactory.getLogger(BattleService.class);
    private Environment env;
    private CharacterRepository characterRepository;
    private ActorRepository actorRepository;
    private SocialMediaRepository socialMediaRepository;

    // Constructor injection
    @Autowired
    public CharacterService(
            final Environment env,
            CharacterRepository characterRepository,
            ActorRepository actorRepository,
            SocialMediaRepository socialMediaRepository) {
        this.env = env;
        this.characterRepository = characterRepository;
        this.actorRepository = actorRepository;
        this.socialMediaRepository = socialMediaRepository;
    }

    // Methods
    /**
     * Returns a list of BattleBean's of all the battles in the database
     *
     * @return
     */
    public List<CharacterDTO> getAllCharacters() {
        List<CharacterDTO> characters = new ArrayList<>();

        // Retrieving all the battles from the database
        characterRepository.findAll().forEach(characterEntity -> characters.add(buildCharacterDTO(characterEntity)));

        // Returning the battles
        return characters;
    }

    /**
     * Returns a battle by its ID
     *
     * @param id
     * @return
     */
    public CharacterDTO getCharacterById(Long id) throws Exception {
        Optional<CharacterRelDAO> battleEntity = characterRepository.findById(id);
        return buildCharacterDTO(
                battleEntity.orElseThrow(
                        () -> new ResourceNotFoundException(env.getProperty("error.message.notfound"))
                )
        );
    }

    /**
     * Builds the character DTO
     *
     * @param characterEntity
     * @return
     */
    public CharacterDTO buildCharacterDTO(CharacterRelDAO characterEntity) {

        // Getting all the actors for this character
        List<ActorRelDAO> actorsDAO = actorRepository.findActorsByCharacter(characterEntity.getId());

        // Building the actors list
        List<ActorDTO> actorsDTO = new ArrayList<>();
        actorsDAO.forEach(actorDAO -> {

            // Getting the social media of the actor
            List<SocialMediaRelDAO> socialMediaDAOList = socialMediaRepository
                    .findSocialMediaByActorID(actorDAO.getId());

            // Building the social media DTO
            List<SocialMediaDTO> socialMedia = new ArrayList<>();
            socialMediaDAOList.forEach(socialMediaEntity -> {
                SocialMediaDTO socialMediaDTO = SocialMediaDTO.builder()
                        .id(socialMediaEntity.getId())
                        .name(socialMediaEntity.getName())
                        .link(socialMediaRepository.getLinkByActorIDAndSocialMediaID(actorDAO.getId(), socialMediaEntity.getId()))
                        .build();
                socialMedia.add(socialMediaDTO);
            });

            // Building the actor DTO for each actor entity found
            ActorDTO actorDTO = ActorDTO.builder()
                    .id(actorDAO.getId())
                    .name(actorDAO.getName())
                    .alias(actorDAO.getAlias())
                    .description(actorDAO.getDescription())
                    .image(actorDAO.getImageURL())
                    .socialMedia(socialMedia)
                    .build();
            actorsDTO.add(actorDTO);
        });

        // Building the character DTO
        CharacterDTO characterDTO = CharacterDTO.builder()
                .id(characterEntity.getId())
                .name(characterEntity.getName())
                .description(characterEntity.getDescription())
                .image(characterEntity.getImageURL())
                .actors(actorsDTO)
                .build();

        return characterDTO;
    }

}
