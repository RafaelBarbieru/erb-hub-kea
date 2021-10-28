package com.anpora.erbhub.utils;

import org.springframework.stereotype.Component;

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

}
