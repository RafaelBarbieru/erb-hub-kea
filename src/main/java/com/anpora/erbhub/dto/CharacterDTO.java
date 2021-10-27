package com.anpora.erbhub.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Bean class for an ERB character
 */
@Data
@Builder
public class CharacterDTO {

    private Long id;
    private String name;
    private String alias;
    private String character;
    private String contact;

}

