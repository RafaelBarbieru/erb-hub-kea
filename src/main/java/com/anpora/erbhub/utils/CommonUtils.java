package com.anpora.erbhub.utils;

import com.anpora.erbhub.dto.ActorDTO;
import com.anpora.erbhub.dto.CharacterDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Common utils for the application
 */
@Component
public class CommonUtils {

    /**
     * Converts <code>durationInSeconds</code> to the format mm:ss
     * @param durationInSeconds
     * @return
     */
    public String getFormattedDuration(Integer durationInSeconds) {
        int seconds = durationInSeconds % 60;
        int minutes = durationInSeconds / 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Returns a string with all the actors of a battle separated with commas
     * @param characters
     * @return
     */
    public List<ActorDTO> getActorsFromCharacters(List<CharacterDTO> characters) {
        List<ActorDTO> allActors = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        characters.forEach(character -> allActors.addAll(character.getActors()));
        return allActors;
    }

}
