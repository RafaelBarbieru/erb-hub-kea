package com.anpora.erbhub.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Bean class for an ERB character
 */
@Data
@Builder
public class CharacterDTO {

    private Long id;
    private String name;
    private String description;
    private String image;
    private List<ActorDTO> actors;

    @Override
    public String toString() {
        return name;
    }
}

