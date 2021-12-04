package com.anpora.erbhub.dao.document;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CharacterDocDAO {

    @Id
    private String id;
    private String name;
    private String description;
    private ActorDocDAO actor;

}
