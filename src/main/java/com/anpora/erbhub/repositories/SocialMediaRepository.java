package com.anpora.erbhub.repositories;

import com.anpora.erbhub.entities.ActorEntity;
import com.anpora.erbhub.entities.CharacterEntity;
import com.anpora.erbhub.entities.SocialMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 */
public interface SocialMediaRepository extends JpaRepository<SocialMediaEntity, Long> {

    @Query(nativeQuery = true, value =
            "SELECT S.*, ASM.link FROM social_media S " +
                    "JOIN actors_social_media ASM ON S.id = ASM.social_media_id " +
                    "JOIN actors A ON ASM.actor_id = A.id " +
                    "WHERE A.id = ?1")
    List<SocialMediaEntity> findSocialMediaByActorID(Long id);

}
