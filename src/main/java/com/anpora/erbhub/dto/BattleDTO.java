package com.anpora.erbhub.dto;

import lombok.Builder;
import lombok.Data;

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
    private Integer durationInSeconds;
    private String publicationDate;
    private List<CharacterDTO> cast;
    private String lyrics;
    private String spotifyLink;
    private String coverPictureURL;

}
