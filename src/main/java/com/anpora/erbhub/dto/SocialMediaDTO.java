package com.anpora.erbhub.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Bean class for a social media platform
 */
@Data
@Builder
public class SocialMediaDTO {

    private Long id;
    private String name;
    private String link;

}
