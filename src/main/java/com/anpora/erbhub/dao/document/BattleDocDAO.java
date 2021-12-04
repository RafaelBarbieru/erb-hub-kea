package com.anpora.erbhub.dao.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "battles")
public class BattleDocDAO {

    @Id
    private String id;
    private String name;
    private Integer duration;
    @Field("publication_date") private String publicationDate;
    private String lyrics;
    @Field("youtube_link") private String youtubeLink;
    @Field("spotify_link") private String spotifyLink;
    @Field("cover_image") private String image;
    private List<CharacterDocDAO> characters;

}
