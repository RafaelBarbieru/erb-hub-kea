package com.anpora.erbhub.controllers;

import com.anpora.erbhub.dto.BattleDTO;
import com.anpora.erbhub.exceptions.ResourceNotFoundException;
import com.anpora.erbhub.services.BattleService;
import com.anpora.erbhub.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Controller for all the battles-related views in the application
 */
@Controller
public class WebBattleController {

    // Dependencies
    private BattleService battleService;
    private CommonUtils commonUtils;

    // Constructor injection
    @Autowired
    public WebBattleController(
            BattleService battleService,
            CommonUtils commonUtils) {
        this.battleService = battleService;
        this.commonUtils = commonUtils;
    }

    // Endpoints
    @RequestMapping("/")
    public String index(Model model) throws Exception {
        return battles(model);
    }

    @RequestMapping("/battles")
    public String battles(Model model) throws Exception {
        List<BattleDTO> battles = battleService.getAllBattles();
        model.addAttribute("battles", battles);
        return "battles";
    }

    @RequestMapping("/battle/{id}")
    public String battle(Model model, @PathVariable Long id) throws Exception {
        try {
            BattleDTO battle = battleService.getBattleById(id);
            model.addAttribute("battle", battle);
            return "battle";
        } catch (ResourceNotFoundException ex) {
            return "battle_not_found";
        }
    }

}
