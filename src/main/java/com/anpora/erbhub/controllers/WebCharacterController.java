package com.anpora.erbhub.controllers;

import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.dto.CharacterDTO;
import com.anpora.erbhub.exceptions.ResourceNotFoundException;
import com.anpora.erbhub.services.CharacterService;
import com.anpora.erbhub.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Controller for all the characters-related views in the application
 */
@Controller
public class WebCharacterController {

    // Dependencies
    private CharacterService characterService;
    private CommonUtils commonUtils;

    // Constructor injection
    @Autowired
    public WebCharacterController(
            CharacterService characterService,
            CommonUtils commonUtils) {
        this.characterService = characterService;
        this.commonUtils = commonUtils;
    }

    // Endpoints
    @RequestMapping("/characters")
    public String characters(Model model) throws Exception {
        List<CharacterDTO> characters = characterService.getAllCharacters();
        model.addAttribute("characters", characters);
        return "characters";
    }

    @RequestMapping("/character/{id}")
    public String character(Model model, @PathVariable Long id) throws Exception {
        try {
            CharacterDTO character = characterService.getCharacterById(id);
            model.addAttribute("character", character);
            return "character";
        } catch (ResourceNotFoundException ex) {
            return "character_not_found";
        }
    }

}
