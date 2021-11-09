package com.anpora.erbhub.controllers;

import com.anpora.erbhub.dto.ActorDTO;
import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.dto.CharacterDTO;
import com.anpora.erbhub.exceptions.ResourceNotFoundException;
import com.anpora.erbhub.services.ActorService;
import com.anpora.erbhub.services.BattleService;
import com.anpora.erbhub.services.CharacterService;
import com.anpora.erbhub.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Controller for all the actors-related views in the application
 */
@Controller
public class WebActorController {

    // Dependencies
    private ActorService actorService;
    private CommonUtils commonUtils;

    // Constructor injection
    @Autowired
    public WebActorController(
            ActorService actorService,
            CommonUtils commonUtils) {
        this.actorService = actorService;
        this.commonUtils = commonUtils;
    }

    // Endpoints
    @RequestMapping("/actors")
    public String characters(Model model) throws Exception {
        Map<Long, List<CharacterDTO>> charactersOfActors = new HashMap<>();
        List<ActorDTO> actors = actorService.getAllActors();
        actors.forEach(actor ->
                charactersOfActors.put(actor.getId(), actorService.getCharactersOfActor(actor.getId())));
        model.addAttribute("characters", charactersOfActors);
        model.addAttribute("actors", actors);
        return "actors";
    }

    @RequestMapping("/actor/{id}")
    public String character(Model model, @PathVariable Long id) throws Exception {
        try {
            List<BattleDTO> battles = actorService.getBattlesOfActor(id);
            List<CharacterDTO> characters = actorService.getCharactersOfActor(id);
            ActorDTO actor = actorService.getActorById(id);
            model.addAttribute("battles", battles);
            model.addAttribute("characters", characters);
            model.addAttribute("actor", actor);
            return "actor";
        } catch (ResourceNotFoundException ex) {
            return "actor_not_found";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }

}
