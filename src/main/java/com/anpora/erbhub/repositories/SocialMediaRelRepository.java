package com.anpora.erbhub.repositories;

import com.anpora.erbhub.dao.SocialMediaRelDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 */
public interface SocialMediaRelRepository extends JpaRepository<SocialMediaRelDAO, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM social_media S " +
                    "JOIN actors_social_media ASM ON S.id = ASM.social_media_id " +
                    "JOIN actors A ON ASM.actor_id = A.id " +
                    "WHERE A.id = ?1")
    List<SocialMediaRelDAO> findSocialMediaByActorID(Long id);

    @Query(nativeQuery = true, value =
            "SELECT ASM.link FROM actors_social_media ASM " +
                    "JOIN social_media S ON S.id = ASM.social_media_id " +
                    "JOIN actors A ON ASM.actor_id = A.id " +
                    "WHERE A.id = ?1 AND S.id = ?2")
    String getLinkByActorIDAndSocialMediaID(long actorID, long socialMediaID);

}
