package com.anpora.erbhub.dao.document;

import com.anpora.erbhub.dao.relational.SocialMediaRelDAO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Entity class for an actor
 */
@Data
public class ActorDocDAO {

    @Id
    private String id;
    private String name;
    private String alias;
    private String description;
    @Field("image") private String imageURL;
    @Field("social_media") private List<SocialMediaDocDAO> socialMedia;

}
