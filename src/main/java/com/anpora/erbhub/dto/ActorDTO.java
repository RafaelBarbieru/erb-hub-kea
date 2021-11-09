package com.anpora.erbhub.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Bean class for an actor
 */
@Data
@Builder
public class ActorDTO {

    private Long id;
    private String name;
    private String alias;
    private String description;
    private String image;
    private List<SocialMediaDTO> socialMedia;

    @Override
    public String toString() {
        return name;
    }
}
