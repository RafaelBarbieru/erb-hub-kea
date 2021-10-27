package com.anpora.erbhub.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Bean class for an ERB person
 */
@Data
@Builder
public class PersonDTO {

    private Long id;
    private String name;
    private String alias;
    private String contact;

}
