package com.anpora.erbhub.utils;

import com.anpora.erbhub.dao.relational.ActorRelDAO;
import com.anpora.erbhub.dao.relational.BattleRelDAO;
import com.anpora.erbhub.dao.relational.CharacterRelDAO;
import com.anpora.erbhub.dao.relational.SocialMediaRelDAO;
import com.anpora.erbhub.dto.ActorDTO;
import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.dto.CharacterDTO;
import com.anpora.erbhub.dto.SocialMediaDTO;

import java.util.ArrayList;
import java.util.List;

public class DTOBuilder {

    public static BattleDTO buildBattleFull(
            BattleRelDAO battleEntity,
            List<CharacterDTO> characters
    ) {
        return BattleDTO.builder()
                .id(battleEntity.getId())
                .name(battleEntity.getName())
                .duration(battleEntity.getDuration())
                .publicationDate(battleEntity.getPublicationDate())
                .lyrics(battleEntity.getLyrics())
                .youtubeLink(battleEntity.getYoutubeLink())
                .spotifyLink(battleEntity.getSpotifyLink())
                .image(battleEntity.getCoverImageURL())
                .characters(characters)
                .build();
    }

    public static BattleDTO buildBattleSimple(BattleRelDAO battleEntity) {
        return BattleDTO.builder()
                .id(battleEntity.getId())
                .name(battleEntity.getName())
                .duration(battleEntity.getDuration())
                .publicationDate(battleEntity.getPublicationDate())
                .lyrics(battleEntity.getLyrics())
                .youtubeLink(battleEntity.getYoutubeLink())
                .spotifyLink(battleEntity.getSpotifyLink())
                .image(battleEntity.getCoverImageURL())
                .characters(null) // It's a simple build
                .build();
    }

    public static ActorDTO buildActorFull(ActorRelDAO actor, List<SocialMediaDTO> socialMedia) {
        return ActorDTO.builder()
                .id(actor.getId())
                .name(actor.getName())
                .alias(actor.getAlias())
                .description(actor.getDescription())
                .image(actor.getImageURL())
                .socialMedia(socialMedia)
                .build();
    }

    public static ActorDTO buildActorSimple(ActorRelDAO actor) {
        return ActorDTO.builder()
                .id(actor.getId())
                .name(actor.getName())
                .alias(actor.getAlias())
                .description(actor.getDescription())
                .image(actor.getImageURL())
                .socialMedia(null) // It's a simple build
                .build();
    }

    public static CharacterDTO buildCharacterFull(CharacterRelDAO character, List<ActorDTO> actors) {
        return CharacterDTO.builder()
                .id(character.getId())
                .name(character.getName())
                .description(character.getDescription())
                .image(character.getImageURL())
                .actors(actors)
                .build();
    }

    public static CharacterDTO buildCharacterSimple(CharacterRelDAO character) {
        return CharacterDTO.builder()
                .id(character.getId())
                .name(character.getName())
                .description(character.getDescription())
                .image(character.getImageURL())
                .actors(null) // It's a simple build
                .build();
    }

    public static SocialMediaDTO buildSocialMedia(SocialMediaRelDAO account) {
        return SocialMediaDTO.builder()
                .id(account.getId())
                .name(account.getName())
                .link(account.getLink())
                .build();
    }

}
