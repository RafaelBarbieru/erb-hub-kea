package com.anpora.erbhub.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Bean class for an ERB Battle/Video
 */
@Data
@Builder
public class BattleDTO {

    private Long id;
    private String name;
    private Integer duration;
    private Date publicationDate;
    private String lyrics;
    private String youtubeLink;
    private String spotifyLink;
    private String image;
    private List<CharacterDTO> characters;

}
