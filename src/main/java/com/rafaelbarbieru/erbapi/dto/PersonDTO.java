package com.rafaelbarbieru.erbapi.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Rafael Barbieru
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
