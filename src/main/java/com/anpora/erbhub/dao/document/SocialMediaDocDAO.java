package com.anpora.erbhub.dao.document;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class SocialMediaDocDAO {

    @Id
    private String id;
    private String name;
    private String link;

}
